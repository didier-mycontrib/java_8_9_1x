package tp.avec_lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import tp.data.Product;
import tp.sam.IsMapped;
import tp.util.ProductUtil;

public class AppGestionProduitsAvecLambda {
	
	public static void testDefaultAndStaticMethodOfInterface() {
		int age=20;
		IsMapped.printIfTrue(age>=18, "adulte");
		IsMapped.printEither(age>=18, "majeur" , "mineur");
	}
	

	
	static void textBizzare() {
		List<Product> listProd = ProductUtil.initSampleProductList();
		List<Product> subListCheapProductsV2Bis = 
				ProductUtil.extractSubListByPredicate(listProd,	(new ProductUtil())::isCheapProductNonStatic); 
				// ProductUtil::isCheapProductNonStatic possible mais pas evident/intuitif
		System.out.println("subListCheapProductsV2Bis (no static)="+ subListCheapProductsV2Bis);
	}
	

	public static void main(String[] args) {
		
		
		
		List<Product> listProd = ProductUtil.initSampleProductList();
		System.out.println("listProd="+listProd);
		
		List<Product> subListCheapProducts = 
				//ProductUtil.extractSubListByPredicate(listProd,	(Product p) -> { return p.getPrice() <= 100 ; });
				//ProductUtil.extractSubListByPredicate(listProd,	(p) -> { return p.getPrice() <= 100 ; });
		        ProductUtil.extractSubListByPredicate(listProd,	(p) -> p.getPrice() <= 100);
				//ProductUtil.extractSubListByPredicate(listProd,	p -> p.getPrice() <= 100);
		System.out.println("subListCheapProducts (lambda)="+subListCheapProducts);
		List<Product> subListCheapProductsV2 = 
				ProductUtil.extractSubListByPredicate(listProd,	ProductUtil::isCheapProduct);
		
		System.out.println("subListCheapProductsV2 (static function reference)="+subListCheapProductsV2);
		
		List<Product> subListCheapProductsV3 = 
				ProductUtil.extractSubListByPredicate(listProd,	Product::isCheaperThan100);
		
		System.out.println("subListCheapProductsV3 (no static function reference)="+subListCheapProductsV3);
		
		//textBizzare();
			
		List<Product> subListComputerProducts = 
				ProductUtil.extractSubListByPredicate(listProd,p -> p.getLabel().contains("computer"));
		System.out.println("subListComputerProducts="+subListComputerProducts);
		List<Product> subListComputerProductsV2 = 
				ProductUtil.extractSubListByPredicate(listProd,ProductUtil::isComputerProduct);
		System.out.println("subListComputerProductsV2="+subListComputerProductsV2);
		
		Collections.sort(listProd, (p1,p2) ->  Double.compare(p1.getPrice(), p2.getPrice()) );
		System.out.println("listProd par prix croissant="+listProd);
		
		Collections.sort(listProd, (p1,p2) -> p1.getLabel().compareTo(p2.getLabel()) );
		System.out.println("listProd trie par label="+listProd);
		
		testDefaultAndStaticMethodOfInterface();
		
		List<String> categories = Arrays.asList( "Computer" , "Printer" );
		Map<String,List<Product>> mapProducts = ProductUtil.buildMapFromAssociatedValues(categories , listProd,
				(keyVal,p) -> p.getLabel().toLowerCase().startsWith(keyVal.toLowerCase()) );
		System.out.println("mapProducts (by categories)="+mapProducts);
		
	}

}
