package tp.j15_16_17;
//import static tp.j15_16_17.MyRecords.*;

public class TestRecordRestrictions {

	public static void main(String[] args) {
		
        //NB: héritage impossible entre records
		//mais implémentation d'interface(s) possible
		
		MyRecords.ProductL0 p0 = new MyRecords.ProductL0(1L,"productA", 12.5);
		
		java.lang.Record anyRecordRef =p0; 
		System.out.println(anyRecordRef.toString());
		
		MyRecords.Product pRef  = p0;
		System.out.println(pRef.toString());
		System.out.println("id=" + pRef.id() +  " label=" + pRef.label() +  " price=" + pRef.price());
		
		MyRecords.ProductL2 p2 = new MyRecords.ProductL2(2L,"productB", 15.5 , "very good" ,
				                                         new MyRecords.CategoryL0(1L, "category1"));
		System.out.println(p2.toString());
		MyRecords.ProductI2 pRefI2 = p2;
		System.out.println(pRefI2.toString() + "with features=" +  pRefI2.features()
		       + " and with category=" + pRefI2.category());
		
		
		//test temporaire (petite variante):
		MyRecords.ICategory cat = new MyRecords.ICategory.CategoryR0(3L, "cat3");
		System.out.println(cat.toString());
		//ça fonctionne mais à appliquer à 2 niveaux (records imbriqués dans interface)
		//mais interface pas imbriquée dans MyRecords
		
		
	}
	
	

}
