
package ma.patisserie.repository;

import ma.patisserie.model.Produit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitRepository {


    private List<Produit> produits;

    
    public ProduitRepository() {
        produits = new ArrayList<>();
    }

   
    public void ajouterProduit(Produit produit) {
        if (produit != null) {
            produits.add(produit);
        }
    }

    
    public Optional<Produit> rechercherParId(int id) {
        return produits.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    
    public List<Produit> listerProduits() {
        return new ArrayList<>(produits);
    }

    
    public boolean supprimerProduit(int id) {
        return produits.removeIf(p -> p.getId() == id);
    }

    public boolean existe(int id) {
        return produits.stream()
                .anyMatch(p -> p.getId() == id);
    }

    public int nombreProduits() {
        return produits.size();
    }
}
