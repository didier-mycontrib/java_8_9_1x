package tp.avec_lambda;

import java.util.List;
import java.util.stream.Collectors;

import tp.data.Product;
import tp.util.ProductUtil;

public class AppAutresExemplesStreams {

	public static void main(String[] args) {
		System.out.println("nb_coeurs="+Runtime.getRuntime().availableProcessors());
		//Long taille = 2048L;  //stream: 35ms , parallelStream: 19ms
		Long taille = 2048 * 2048L; //stream: 3397ms , parallelStream: 1193ms
		List<Product> listProd = ProductUtil.initBigSampleProductList(taille);
		System.out.println("listProd size="+listProd.size());
		viaStream(listProd);
		viaParallelStream(listProd);
	}
	
	public static String withThreadName(String s){
		return s + "_" +Thread.currentThread().getName();
	}
	
	
	
	public static void viaStream(List<Product> listProd){
		double fraisPort = 1.2;
		double coeffAugmentation = 1.05;
		Long td = System.nanoTime();
		String firstPartOfListProdRes  = 
		listProd.stream()
		 .map((p)-> new Product(p.getId(), 
				                withThreadName(p.getLabel()),
				                p.getPrice()+fraisPort , 
				                p.getFeatures()))
		 .sorted((p1,p2)->Double.compare(p1.getPrice(), p2.getPrice()))
         .map((p)-> (new Product(p.getId(), 
        		                p.getLabel(), 
        		                p.getPrice()* coeffAugmentation , 
        		                withThreadName(p.getFeatures()) )).toStringEx())
         .limit(20) //pour l'affichage , on se limite ici aux 20premiers elements
         //.toList();
         .collect(Collectors.joining(";"));
		
		Long tf = System.nanoTime();
		System.out.println("viaStream, temps d'execution (ms)=" + (tf-td)/ 1000000);
		System.out.println("viaStream, listProdRes " + firstPartOfListProdRes);
	}
	
	public static void viaParallelStream(List<Product> listProd){
		double fraisPort = 1.2;
		double coeffAugmentation = 1.05;
		Long td = System.nanoTime();
		String firstPartOfListProdRes  = 
		listProd.parallelStream()
		.map((p)-> new Product(p.getId(), 
                withThreadName(p.getLabel()),
                p.getPrice()+fraisPort , 
                p.getFeatures()))
		.sorted((p1,p2)->Double.compare(p1.getPrice(), p2.getPrice()))
		.map((p)-> (new Product(p.getId(), 
                p.getLabel(), 
                p.getPrice()* coeffAugmentation , 
                withThreadName(p.getFeatures()) )).toStringEx())
         .limit(20) //pour l'affichage , on se limite aux 20premiers elements
         //.toList();
         .collect(Collectors.joining(";"));
		
		Long tf = System.nanoTime();
		System.out.println("viaParallelStream, temps d'execution (ms)=" + (tf-td)/ 1000000);
		System.out.println("viaParallelStream, firstPartOfListProdRes= " + firstPartOfListProdRes );
	}

}
