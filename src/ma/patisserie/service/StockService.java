
package ma.patisserie.service;

import ma.patisserie.model.Produit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StockService {

    private final List<Produit> produits = new ArrayList<>();

    public void ajouterProduit(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException(
                "Le produit ne peut pas être null."
            );
        }

        if (produits.contains(produit)) {
            throw new IllegalArgumentException(
                "Ce produit existe déjà."
            );
        }

        produits.add(produit);
    }

    public List<Produit> getProduits() {
        return new ArrayList<>(produits);
    }

    
    public List<Produit> rechercherProduits(String motCle) {
        return produits.stream()
                .filter(p -> p.getNom() != null
                        && p.getNom().toLowerCase()
                        .contains(motCle.toLowerCase()))
                .toList();
    }

   
    
    public List<Produit> trierParPrix() {
        return produits.stream()
                .sorted(Comparator.comparingDouble(Produit::getPrix))
                .toList();
    }

   
    public long compterProduitsDisponibles() {
        return produits.stream()
                .filter(Produit::estDisponible)
                .count();
    }

    
    public void afficherProduits() {
        produits.stream()
                .forEach(System.out::println);
    }

   
    public List<String> obtenirNomsProduits() {
        return produits.stream()
                .map(Produit::getNom)
                .toList();
    }
    
    
    public List<Produit> produitsEnRupture() {
        return produits.stream()
                .filter(p -> p.getQuantite() == 0)
                .toList();
    }

    
    public double calculerValeurStock() {
        return produits.stream()
                .mapToDouble(p -> p.getPrix() * p.getQuantite())
                .sum();
    }

}
