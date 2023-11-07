package tp.j15_16_17;

public class MyRecords {
	
	//interface Xx , XxI1 , XxI2
	//record XxL0 , XxL1 , XxL2 (Level 0 , Level 1, Level 2 , ...)
	
	public interface Category {Long id();String label();}
	public record CategoryL0(Long id,String label) implements Category {		
	}
	
	public interface Product {Long id();	String label();	Double price();}
	public record ProductL0(Long id,String label,Double price) implements Product {		
	}
	
	//ERROR: public record ProductL1(...) extends RecordL0 
	//no inheritance beetween record 
	//(because a record is already a subclass of abstract class java.lang.Record)
	//and only one single superclass with java extends

	public interface ProductI1 extends Product {String features();}
	public record ProductL1(Long id,String label,Double price,String features) implements ProductI1{		
	}
	
	public interface ProductI2 extends ProductI1 {Category category();}
	public record ProductL2(Long id,String label,Double price,String features,Category category) implements ProductI2{		
	}
	
	//VARIANTE (ESSAI):
	public interface ICategory {
		Long id();
		String label();
		
		record CategoryR0(Long id,String label) implements ICategory {		
		}
		
		record CategoryR1(Long id,String label,int level) implements ICategory {		
		}
	}
}
