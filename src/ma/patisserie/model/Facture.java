package ma.patisserie.partie2;

import java.time.LocalDateTime;

public class Facture {

    private String numero;
    private LocalDateTime dateEmission;
    private double montantTotal;
    private Commande commande;     // la commande qui a généré cette facture

    public Facture(String numero, Commande commande) {
        this.numero = numero;
        this.commande = commande;
        this.dateEmission = LocalDateTime.now();
        this.montantTotal = calculerMontant();
    }

    public double calculerMontant() {
        return commande.calculerTotal();
    }

    public void afficher() {
        System.out.println("===== FACTURE " + numero + " =====");
        System.out.println("Client  : " + commande.getClient().getNom());
        System.out.println("Vendeur : " + commande.getVendeur().getNom());
        commande.getLignes().forEach(l -> System.out.println(" - " + l));
        System.out.println("TOTAL   : " + montantTotal + " DH");
    }

    public String getNumero() { return numero; }
    public double getMontantTotal() { return montantTotal; }

    // Pas de commande.toString() ici : évite les boucles Commande <-> Facture
    @Override
    public String toString() {
        return "Facture{numero='" + numero + "', dateEmission=" + dateEmission
                + ", montantTotal=" + montantTotal + ", commandeId=" + commande.getId() + "}";
    }
}
