package tp.j15_16_17;

public class TestTextBlocApp {

	public static void main(String[] args) {
		oldStyleWithoutTextBloc();
		newStyleWithTextBloc();
	}
	
	public static void oldStyleWithoutTextBloc() {
		String myJsonString 
		  = "{\r\n" + "\"username\" : \"JeanBon\",\r\n" + "\"country\" : \"France\"\r\n" + "}";
		System.out.println("myJsonString="+myJsonString);
	}
	
    public static void newStyleWithTextBloc() {
    String myJsonStringAsTextBloc = """
	{
     "username" : "AlexTherieur",
	 "country" : "Belgique"
	}
	""";
	System.out.println("myJsonStringAsTextBloc="+myJsonStringAsTextBloc);
	}

}
