package ma.patisserie.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Revision {

 
    record Point(int x, int y) {
        Point {                                  
            if (x < 0 || y < 0) throw new IllegalArgumentException("coordonnée négative");
        }
        double distance() { return Math.sqrt(x * x + y * y); }   
    }

    private int compteur = 100;

    
    static class Statique {
        String dire() { return "static nested : khdama bla instance dyal Revision"; }
    }


    class Interne {
        String dire() { return "inner : compteur dyal outer = " + compteur; }
    }

    public static void main(String[] args) {

        Runnable r = () -> System.out.println("Salam mn lambda");       
        r.run();

        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b; 
        System.out.println("3 + 4 = " + addition.apply(3, 4));

        Predicate<String> motLong = s -> s.length() > 4;      
        Function<String, Integer> taille = String::length;    
        System.out.println("'Youssef' long ? " + motLong.test("Youssef")
                + " | taille = " + taille.apply("Youssef"));

        List<String> noms = new ArrayList<>(List.of("Sara", "Youssef", "Amine", "Sara", "Imane", "Omar"));
        noms.sort((a, b) -> a.length() - b.length());          
        System.out.println("Trié par longueur : " + noms);

        Comparator<String> ancien = new Comparator<>() {
            @Override
            public int compare(String a, String b) { return a.compareTo(b); }
        };
        noms.sort(ancien);
        System.out.println("Trié alphabétiquement : " + noms);

   
        Point p = new Point(3, 4);
        System.out.println(p + " | x=" + p.x() + " | distance=" + p.distance());
        System.out.println("equals auto : " + p.equals(new Point(3, 4)));   // true

        
        Statique s = new Statique();                    
        Revision outer = new Revision();                
        Revision.Interne in = outer.new Interne();
        System.out.println(s.dire());
        System.out.println(in.dire());

        class Locale {                                
            String hello() { return "classe locale"; }
        }
        System.out.println(new Locale().hello());

        
        List<String> resultat = noms.stream()      
                .filter(motLong.negate())          
                .map(String::toUpperCase)          
                .distinct()                        
                .sorted()                         
                .collect(Collectors.toList());     
        System.out.println("Résultat stream : " + resultat);

        int sommeCarres = List.of(1, 2, 3, 4).stream()
                .map(n -> n * n)
                .reduce(0, Integer::sum);        
        System.out.println("Somme des carrés = " + sommeCarres);
      
List<String> troisPremiers = noms.stream()
        .limit(3)
        .toList();

System.out.println("Trois premiers : " + troisPremiers);


List<String> sansDeuxPremiers = noms.stream()
        .skip(2)
        .toList();

System.out.println("Sans les deux premiers : " + sansDeuxPremiers);



boolean existe = noms.stream()
        .anyMatch(nom -> nom.startsWith("A"));

System.out.println("Un nom commence par A ? " + existe);
    }
}
