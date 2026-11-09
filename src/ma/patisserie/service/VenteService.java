package ma.patisserie.partie2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Couche "service" : contient la logique du diagramme de séquence
public class VenteService {

    private final List<Commande> commandes = new ArrayList<>();
    private int prochainId = 1;

    // creerCommande(client, vendeur)  ->  new Commande(...)
    public Commande creerCommande(Client client, Utilisateur vendeur) {
        Commande c = new Commande(prochainId++, client, vendeur);
        commandes.add(c);
        return c;
    }

    // Boucle "pour chaque produit" + alt [stock suffisant / insuffisant]
    public boolean ajouterProduit(Commande commande, Produit produit, int quantite) {
        if (!produit.verifierStock(quantite)) {
            System.out.println("Stock insuffisant pour " + produit.getNom());
            return false;
        }
        commande.ajouterLigne(new LigneCommande(produit, quantite));
        return true;
    }

    // Bloc opt [Commande sur mesure]
    public void definirSurMesure(Commande commande, String description, LocalDateTime dateRetrait) {
        commande.definirSurMesure(description, dateRetrait);
    }

    // validerCommande() -> calculerTotal() -> montant total
    public double validerCommande(Commande commande) {
        if (commande.getLignes().isEmpty()) {
            throw new IllegalStateException("Impossible de valider une commande vide");
        }
        commande.setStatut("VALIDEE");
        return commande.calculerTotal();
    }

    // enregistrerPaiement(modePaiement) : sauvegarde + stock + facture
    public Facture enregistrerPaiement(Commande commande, String modePaiement) {
        for (LigneCommande l : commande.getLignes()) {
            l.getProduit().diminuerStock(l.getQuantite());   // mettre à jour le stock
        }
        commande.setModePaiement(modePaiement);
        commande.setEstPayee(true);
        commande.setStatut("PAYEE");
        return commande.genererFacture();                    // genererFacture()
    }

    public List<Commande> getCommandes() {
        return Collections.unmodifiableList(commandes);
    }
}
