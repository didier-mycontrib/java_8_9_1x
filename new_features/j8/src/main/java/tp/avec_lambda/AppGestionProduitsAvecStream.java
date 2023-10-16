package tp.avec_lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import tp.data.Product;
import tp.util.ProductUtil;

public class AppGestionProduitsAvecStream {
	public static void main(String[] args) {

		List<Product> listProd = ProductUtil.initSampleProductList();
		System.out.println("listProd="+listProd);
        
        //on va enchainer a partir de listProd.stream()
        //.sorted ( ... ) avec une lambda pour trier par ordre de prix croissant
        //.filter(...) avec une lambda qui filtre les produits prix >=100
        //.map (...)  avec une lambda qui va transformer les produits , label en majuscules
        //.collect(Collectors.toList());
		
		//listProd = Collections.unmodifiableList(listProd); //depuis java 10,11
		//listProd.add(new Product(99L,"prod99",99.99,"abc")); //interdit si unmodifiable/immutable
		
        List<Product> listeProduitsTriesFiltresEtTransformes =
        		listProd.stream()
        		 .filter((p)->p.getPrice()>=100)
                 .sorted((p1,p2)->Double.compare(p1.getPrice(), p2.getPrice()))
                 .map((p)-> new Product(p.getId(), p.getLabel().toUpperCase(), p.getPrice() , p.getFeatures()))
                // .map((p) -> { p.setLabel(p.getLabel().toUpperCase()); return p; })
                 //.collect(Collectors.toList()); //depuis java 8
                 .toList();//ok en java 17
        
        //exemple de lambda pour .map(...)
        // (p) -> { p.setLabel(p.getLabel().toUpperCase()); return p; }
        
        //afficher la nouvelle liste construite.
        System.out.println("liste triée filtrée et transformée:\n" + listeProduitsTriesFiltresEtTransformes);
        System.out.println("listeProd modifiée ou pas:"+listProd);
        
       //Version avec affichage direct en fin d'enchainement:
        System.out.println("idem via .forEach():");
        listProd.stream()
                 .filter((p)->p.getPrice()>=100)
                 .sorted((p1,p2)->Double.compare(p1.getPrice(), p2.getPrice()))
                 .map((p) -> { p.setLabel(p.getLabel().toUpperCase()); return p; })
                 .forEach( (p) -> System.out.println(p) );
        
        //"moyenne des prix de tous les produits:"
        Double sommePrix = listProd.stream()
                 .map((p) -> p.getPrice())
                 .reduce(0.0 , (x,y) -> x+y);
        System.out.println("moyenne des prix de tous les produits:"+sommePrix/listProd.size());
        
        Double moyennePrixV2 = listProd.stream()
                .map(p -> p.getPrice())
                .mapToDouble(val -> val).average().orElse(0.0);
                //NB: .mapToDouble() preparer l'opération terminale .average()
                //de type appel de fonction sur un paquet de Double
       System.out.println("moyenne des prix de tous les produits (v2):"+moyennePrixV2);
      
       
       List<String> listeJours= Arrays.asList("lundi" , "mardi" , "mercredi" , "jeudi" ,"vendredi" , "samedi", "dimanche");
      
       //transformer cela en "monday;tuesday;....;sunday" 
       String stringDays = listeJours.stream()
                           .map(AppGestionProduitsAvecStream::traduireJour)
                           .collect(Collectors.joining(";"));
       System.out.println("stringDays="+stringDays);
       
	}
	
	public static String traduireJour(String jourFrancais) {
		String dayOfWeek = "?";
		switch(jourFrancais) {
			case "lundi": dayOfWeek="monday"; break;
			case "mardi": dayOfWeek="tuesday"; break;
			case "mercredi": dayOfWeek="wenesday"; break;
			case "jeudi": dayOfWeek="thursday"; break;
			case "vendredi": dayOfWeek="friday"; break;
			case "samedi": dayOfWeek="saturday"; break;
			case "dimanche": dayOfWeek="sunday"; break;
			default: dayOfWeek = "unknown";
		}
		return dayOfWeek;
	}
}
