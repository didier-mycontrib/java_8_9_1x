package tp;

public class ExJnaApp {

	public static void main(String[] args) {
		CLibrary.INSTANCE.printf("Hello world via native printf \n");
		
		try {
			CBasicLib.INSTANCE.display("Hello world via native display \n");
			int res = CBasicLib.INSTANCE.addition(5,6);
			System.out.println("res of native addition(5,6) = " +res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
