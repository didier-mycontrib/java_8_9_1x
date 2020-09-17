package tp.concurent.completable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import tp.concurent.task.LongTask;
import tp.data.Product;
import tp.util.MyFileConsumer;
import tp.util.ProductUtil;

public class ProductAsyncWithCompletableFuture {
	
	
	public static String fetchFavoriteCategory(){
		String category = null;
		MyFileConsumer myFileConsumer = new MyFileConsumer();
		int nbTry=0;
		while(category==null && nbTry < 20) {
		   nbTry++;
		   LongTask.simulateLongTask("waiting 2000ms before (re-)trying fetch category file in files/input" ,  2000);
		   category = myFileConsumer.extractNewFileContentIfExists().orElse(null);
		}
		if(category==null) {
			category="other"; //default value
		}
		System.out.println("category="+category);
        return category; 
	}
	
	public static List<Product> fetchProductList(){
		LongTask.simulateLongTask("fetch products , ... async waiting in background ..." ,  1000); 
        /*throw new RuntimeException("exceptionXY");*/ return ProductUtil.initSampleProductList(); 
	}
	
	public static List<Product> fetchProductListByCategory(String category){
		LongTask.simulateLongTask("fetch products , ... async waiting in background ..." ,  1000); 
        /*throw new RuntimeException("exceptionXY");*/ return ProductUtil.initSampleProductListByCategory(category); 
	}
	
	public static Double fetchAcceptableMaxPrice(){
		LongTask.simulateLongTask("fetch acceptable max price , ... async waiting in background ..." ,  2500); 
        /*throw new RuntimeException("exceptionXY");*/ return 900.0; 
	}
	
	

	public static void main(String[] args) {
		System.out.println("debut main / interpreted by " + Thread.currentThread().getName());
		
		
		/*
		CompletableFuture.supplyAsync(ProductAsyncWithCompletableFuture::fetchProductList )
		.thenApply((pList)-> ProductUtil.extractSubListByPredicate(pList, p -> p.getPrice() <= 100) )
        .thenAccept(ProductUtil::displayProductList );*/
		

		CompletableFuture.supplyAsync(ProductAsyncWithCompletableFuture::fetchFavoriteCategory )
		.thenApply(category -> ProductUtil.initSampleProductListByCategory(category) )
		//.thenApply(pList -> ProductUtil.extractSubListByPredicate(pList, p -> p.getPrice() <= 600))
        .thenAccept(ProductUtil::displayProductList );
		
		
		/*
		CompletableFuture<Double> cfFetchingMaxAcceptablePrice =
				CompletableFuture.supplyAsync(ProductAsyncWithCompletableFuture::fetchAcceptableMaxPrice );
		
		CompletableFuture.supplyAsync(ProductAsyncWithCompletableFuture::fetchFavoriteCategory )
		.thenApply(category -> ProductUtil.initSampleProductListByCategory(category) )
		.thenCombine(cfFetchingMaxAcceptablePrice, 
				(pList,maxPrice) -> ProductUtil.extractSubListByPredicate(pList, p -> p.getPrice() <= maxPrice))
        .thenAccept(ProductUtil::displayProductList );
		*/
		
		System.out.println("suite main / interpreted by " + Thread.currentThread().getName());
		
		LongTask.simulateLongTask("pause pour eviter arrêt complet du programme avant la fin des taches de fond" ,  2000 * 22);
		System.out.println("fin main / interpreted by " + Thread.currentThread().getName());
	}
}
