
package ma.patisserie.model;

import java.util.Objects;

public class Produit {

    private int id;
    private String nom;
    private double prix;
    private int quantite;
    private Categorie categorie;
    private Fournisseur fournisseur;

    public Produit(int id, String nom, double prix, int quantite,
                   Categorie categorie, Fournisseur fournisseur) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.fournisseur = fournisseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public boolean estDisponible() {
        return quantite > 0;
    }

    public void diminuerStock(int quantiteVendue) {
        if (quantiteVendue <= 0) {
            throw new IllegalArgumentException(
                "La quantité doit être positive."
            );
        }

        if (quantiteVendue > quantite) {
            throw new IllegalArgumentException(
                "Stock insuffisant."
            );
        }

        this.quantite -= quantiteVendue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Produit autre)) return false;
        return id == autre.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produit{id=" + id
                + ", nom='" + nom + '\''
                + ", prix=" + prix
                + ", quantite=" + quantite
                + '}';
    }
}
