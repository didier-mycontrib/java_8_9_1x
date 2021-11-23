package tp.avec_lambda;

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
                 .sorted((p1,p2)->Double.compare(p1.getPrice(), p2.getPrice()))
                 .filter((p)->p.getPrice()>=100)
                 //.map((p)-> new Product(p.getId(), p.getLabel().toUpperCase(), p.getPrice() , p.getFeatures()))
                 .map((p) -> { p.setLabel(p.getLabel().toUpperCase()); return p; })
                 .collect(Collectors.toList());
        
        //exemple de lambda pour .map(...)
        // (p) -> { p.setLabel(p.getLabel().toUpperCase()); return p; }
        
        //afficher la nouvelle liste construite.
        System.out.println("liste triée filtrée et transformée:\n" + listeProduitsTriesFiltresEtTransformes);
        System.out.println("listeProd modifiée ou pas:"+listProd);
        
       //Version avec affichage direct en fin d'enchainement:
        System.out.println("idem via .forEach():");
        listProd.stream()
                 .sorted((p1,p2)->Double.compare(p1.getPrice(), p2.getPrice()))
                 .filter((p)->p.getPrice()>=100)
                 .map((p) -> { p.setLabel(p.getLabel().toUpperCase()); return p; })
                 .forEach( (p) -> System.out.println(p) );
        
        //"moyenne des prix de tous les produits:"
        Double sommePrix = listProd.stream()
                 .map((p) -> p.getPrice())
                 .reduce(0.0 , (x,y) -> x+y);
        System.out.println("moyenne des prix de tous les produits:"+sommePrix/listProd.size());
	}
}
