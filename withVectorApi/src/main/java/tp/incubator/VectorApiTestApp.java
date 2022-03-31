package tp.incubator;
//ne fonctionne pas encore bien (pas reconnu par eclipse 2021-09, ...)
import jdk.incubator.vector.*;

//NB: VectorApi is still in incubator phase in java16 & java17
public class VectorApiTestApp {

	public static void main(String[] args) {
		float[] a = new float[] {1f, 2f, 3f, 4f};
		float[] b = new float[] {5f, 8f, 10f, 12f};
		
		FloatVector first = FloatVector.fromArray(FloatVector.SPECIES_128, a, 0);
		FloatVector second = FloatVector.fromArray(FloatVector.SPECIES_128, b, 0);
		 
		FloatVector result = first
		        .add(second)
		        .pow(2)
		        .neg();
		
		float[] resultArray = new float[4];
		result.intoArray(resultArray, 0);
		System.out.println("resultArray" + resultArray);
	}

}
