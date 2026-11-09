package ma.patisserie.partie2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Revision {

    // =====================================================
    // 1) RECORD (Java 16+) : classe de DONNÉES immuable
    //    Java génère: constructeur, getters x() y(), equals, hashCode, toString
    // =====================================================
    record Point(int x, int y) {
        Point {                                   // "compact constructor" = validation
            if (x < 0 || y < 0) throw new IllegalArgumentException("coordonnée négative");
        }
        double distance() { return Math.sqrt(x * x + y * y); }   // on peut ajouter des méthodes
    }

    // =====================================================
    // 2) CLASSES IMBRIQUÉES (nested classes)
    // =====================================================
    private int compteur = 100;

    // a) static nested : ma kat7tajch objet dyal outer
    static class Statique {
        String dire() { return "static nested : khdama bla instance dyal Revision"; }
    }

    // b) inner class : mrboTa b objet outer, tqder tchouf ses attributs (même private)
    class Interne {
        String dire() { return "inner : compteur dyal outer = " + compteur; }
    }

    public static void main(String[] args) {

        // =====================================================
        // 3) LAMBDA = fonction anonyme :  (paramètres) -> expression
        //    Elle implémente une interface fonctionnelle (1 seule méthode abstraite)
        // =====================================================
        Runnable r = () -> System.out.println("Salam mn lambda");        // 0 paramètre
        r.run();

        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b; // 2 paramètres
        System.out.println("3 + 4 = " + addition.apply(3, 4));

        Predicate<String> motLong = s -> s.length() > 4;      // retourne boolean
        Function<String, Integer> taille = String::length;    // method reference (raccourci)
        System.out.println("'Youssef' long ? " + motLong.test("Youssef")
                + " | taille = " + taille.apply("Youssef"));

        List<String> noms = new ArrayList<>(List.of("Sara", "Youssef", "Amine", "Sara", "Imane", "Omar"));
        noms.sort((a, b) -> a.length() - b.length());          // lambda comme Comparator
        System.out.println("Trié par longueur : " + noms);

        // Avant les lambdas (classe anonyme) -> beaucoup plus verbeux :
        Comparator<String> ancien = new Comparator<>() {
            @Override
            public int compare(String a, String b) { return a.compareTo(b); }
        };
        noms.sort(ancien);
        System.out.println("Trié alphabétiquement : " + noms);

        // =====================================================
        // 4) RECORD en action
        // =====================================================
        Point p = new Point(3, 4);
        System.out.println(p + " | x=" + p.x() + " | distance=" + p.distance());
        System.out.println("equals auto : " + p.equals(new Point(3, 4)));   // true

        // =====================================================
        // 5) Utilisation des classes imbriquées
        // =====================================================
        Statique s = new Statique();                    // direct
        Revision outer = new Revision();                // pour inner il faut un outer d'abord
        Revision.Interne in = outer.new Interne();
        System.out.println(s.dire());
        System.out.println(in.dire());

        class Locale {                                  // classe locale (dans une méthode)
            String hello() { return "classe locale"; }
        }
        System.out.println(new Locale().hello());

        // =====================================================
        // 6) STREAMS : source -> opérations intermédiaires -> opération terminale
        //    Rien ne s'exécute tant qu'il n'y a pas d'opération terminale (lazy)
        // =====================================================
        List<String> resultat = noms.stream()      // source
                .filter(motLong.negate())          // intermédiaire : garde les mots courts
                .map(String::toUpperCase)          // intermédiaire : transforme
                .distinct()                        // intermédiaire : supprime les doublons
                .sorted()                          // intermédiaire : trie
                .collect(Collectors.toList());     // TERMINALE : construit la liste
        System.out.println("Résultat stream : " + resultat);

        int sommeCarres = List.of(1, 2, 3, 4).stream()
                .map(n -> n * n)
                .reduce(0, Integer::sum);          // TERMINALE : 1+4+9+16
        System.out.println("Somme des carrés = " + sommeCarres);
    }
}
