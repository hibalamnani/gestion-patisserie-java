
package ma.patisserie;

import ma.patisserie.model.Categorie;
import ma.patisserie.model.Fournisseur;
import ma.patisserie.model.Produit;
import ma.patisserie.service.StockService;

public class Main {

    public static void main(String[] args) {

        
        Categorie categorie = new Categorie(
                1,
                "Patisseries",
                "Gateaux et viennoiseries"
        );

       
        Fournisseur fournisseur = new Fournisseur(
                1,
                "Fournisseur Central",
                "0600000000",
                "contact@example.com"
        );

        
        Produit p1 = new Produit(
                1, "Croissant", 5.0, 20,
                categorie, fournisseur
        );

        Produit p2 = new Produit(
                2, "Gateau au chocolat", 30.0, 8,
                categorie, fournisseur
        );

        Produit p3 = new Produit(
                3, "Pain au chocolat", 6.0, 0,
                categorie, fournisseur
        );

        
        StockService stock = new StockService();

        stock.ajouterProduit(p1);
        stock.ajouterProduit(p2);
        stock.ajouterProduit(p3);

      
        System.out.println("=== Tous les produits ===");
        stock.afficherProduits();

        
        System.out.println("\n=== Produits disponibles ===");
        System.out.println(
                "Nombre : " + stock.compterProduitsDisponibles()
        );

       
        System.out.println("\n=== Produits tries par prix ===");
        stock.trierParPrix().forEach(System.out::println);

        
        System.out.println("\n=== Noms des produits ===");
        stock.obtenirNomsProduits().forEach(System.out::println);

        
        System.out.println("\n=== Recherche : Gateau ===");
        stock.rechercherProduits("Gateau")
                .forEach(System.out::println);

       
        System.out.println("\n=== Test equals() ===");

        Produit p4 = new Produit(
                1, "Autre nom", 100.0, 5,
                categorie, fournisseur
        );

        System.out.println(
                "p1.equals(p4) : " + p1.equals(p4)
        );

        System.out.println(
                "Memes hashCodes : "
                        + (p1.hashCode() == p4.hashCode())
        );

        
        System.out.println("\n=== Diminuer le stock ===");
        p1.diminuerStock(2);

        System.out.println(
                "Stock restant de Croissant : " + p1.getQuantite()
        );
        System.out.println("\n=== Produits en rupture de stock ===");
stock.produitsEnRupture().forEach(System.out::println);

System.out.println("\n=== Valeur totale du stock ===");
System.out.println(stock.calculerValeurStock());
    }
}
