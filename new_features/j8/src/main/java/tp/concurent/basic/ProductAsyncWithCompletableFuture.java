package tp.concurent.basic;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import tp.concurent.task.LongTask;
import tp.data.Product;
import tp.util.ProductUtil;

public class ProductAsyncWithCompletableFuture {
	
	public static Double extractInitValue(){
		LongTask.simulateLongTask("long computing - p1" ,  2000); 
        /*throw new RuntimeException("exceptionXY");*/ return 2.0; 
	}
	
	public static String fetchFavoriteCategory(){
		LongTask.simulateLongTask("fetch favorite category , ... async waiting in background ..." ,  1000); 
        /*throw new RuntimeException("exceptionXY");*/ return "computer"; 
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
		
		CompletableFuture<Double> cfFetchingMaxAcceptablePrice =
				CompletableFuture.supplyAsync(ProductAsyncWithCompletableFuture::fetchAcceptableMaxPrice );
		
		CompletableFuture.supplyAsync(ProductAsyncWithCompletableFuture::fetchFavoriteCategory )
		.thenApply(category -> ProductUtil.initSampleProductListByCategory(category) )
		.thenCombine(cfFetchingMaxAcceptablePrice, 
				(pList,maxPrice) -> ProductUtil.extractSubListByPredicate(pList, p -> p.getPrice() <= maxPrice))
        .thenAccept(ProductUtil::displayProductList );
		
		
		System.out.println("suite main / interpreted by " + Thread.currentThread().getName());
		
		LongTask.simulateLongTask("pause pour eviter arrêt complet du programme avant la fin des taches de fond" ,  8000);
		System.out.println("fin main / interpreted by " + Thread.currentThread().getName());
	}
}
