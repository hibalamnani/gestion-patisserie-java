package ma.patisserie.partie2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Commande {

    private int id;
    private LocalDateTime dateCommande;
    private LocalDateTime dateRetrait;
    private String statut;            // EN_COURS, VALIDEE, PAYEE
    private boolean surMesure;
    private String description;
    private String modePaiement;
    private boolean estPayee;

    // Associations du diagramme
    private Client client;                                   // Client 1 --passe--> 0..* Commande
    private Utilisateur vendeur;                             // Utilisateur 1 --enregistre--> 0..*
    private final List<LigneCommande> lignes = new ArrayList<>();  // composition 1..*
    private Facture facture;                                 // 0..1

    public Commande(int id, Client client, Utilisateur vendeur) {
        this.id = id;
        this.client = client;
        this.vendeur = vendeur;
        this.dateCommande = LocalDateTime.now();
        this.statut = "EN_COURS";
        this.surMesure = false;
        this.estPayee = false;
    }

    public void ajouterLigne(LigneCommande ligne) {
        lignes.add(ligne);
    }

    public void definirSurMesure(String description, LocalDateTime dateRetrait) {
        this.surMesure = true;
        this.description = description;
        this.dateRetrait = dateRetrait;
    }

    // Total = somme des sous-totaux (Stream + mapToDouble)
    public double calculerTotal() {
        return lignes.stream()
                .mapToDouble(LigneCommande::getSousTotal)
                .sum();
    }

    public Facture genererFacture() {
        if (lignes.isEmpty()) throw new IllegalStateException("Commande vide");
        facture = new Facture(String.format("F-%04d", id), this);
        return facture;
    }

    // ----- Getters / setters utiles -----
    public int getId() { return id; }
    public Client getClient() { return client; }
    public Utilisateur getVendeur() { return vendeur; }
    public String getStatut() { return statut; }
    public boolean isSurMesure() { return surMesure; }
    public boolean isEstPayee() { return estPayee; }
    public Facture getFacture() { return facture; }
    public List<LigneCommande> getLignes() { return Collections.unmodifiableList(lignes); }

    public void setStatut(String statut) { this.statut = statut; }
    public void setModePaiement(String modePaiement) { this.modePaiement = modePaiement; }
    public void setEstPayee(boolean estPayee) { this.estPayee = estPayee; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return id == ((Commande) obj).id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Commande{id=" + id + ", dateCommande=" + dateCommande + ", dateRetrait=" + dateRetrait
                + ", statut='" + statut + "', surMesure=" + surMesure + ", description='" + description
                + "', modePaiement='" + modePaiement + "', estPayee=" + estPayee
                + ", client=" + client.getNom() + ", nbLignes=" + lignes.size() + "}";
    }
}
