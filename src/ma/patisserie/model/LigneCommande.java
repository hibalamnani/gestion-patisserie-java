package ma.patisserie.partie2;

import java.util.Objects;

public class LigneCommande {

    private Produit produit;       // association "concerne" -> Produit
    private int quantite;
    private double prixUnitaire;   // prix au moment de la vente (on le "fige")

    public LigneCommande(Produit produit, int quantite) {
        if (quantite <= 0) throw new IllegalArgumentException("Quantité invalide");
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = produit.getPrix();
    }

    public double getSousTotal() {
        return quantite * prixUnitaire;
    }

    public Produit getProduit() { return produit; }
    public int getQuantite() { return quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LigneCommande autre = (LigneCommande) obj;
        return quantite == autre.quantite
                && Double.compare(prixUnitaire, autre.prixUnitaire) == 0
                && Objects.equals(produit, autre.produit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produit, quantite, prixUnitaire);
    }

    @Override
    public String toString() {
        return "LigneCommande{produit=" + produit.getNom() + ", quantite=" + quantite
                + ", prixUnitaire=" + prixUnitaire + ", sousTotal=" + getSousTotal() + "}";
    }
}
