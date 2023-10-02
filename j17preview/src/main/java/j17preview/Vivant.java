package j17preview;

//public  interface Vivant {
public sealed interface Vivant {
	 record Vertebre(String name, String  p1 , String p2) implements Vivant {}
	 record Invertebre(String name, String  p3) implements Vivant {}
}
