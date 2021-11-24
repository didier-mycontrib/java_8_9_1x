package tp.j15_16_17;

public class TestRecordApp {

	public static void main(String[] args) {
		testRecord();
	}
	
	public static void testRecord() {
		
		OldCustomer oldC1 = new OldCustomer(1,"jean","Bon");
		System.out.println("oldC1 as POJO ="+oldC1.toString());
		
		LombokCustomer lC1 = new LombokCustomer(1,"jean","Bon");
		System.out.println("olC1 as Lombok POJO ="+lC1.toString());
		
		Customer c1 = new Customer(1,"jean","Bon");
		System.out.println("c1 as record ="+c1.toString());
	}
	
    

}
