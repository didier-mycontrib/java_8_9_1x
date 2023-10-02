package tp.j15_16_17;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public class CatFactClass {
	private String fact;
	private int length;
	
	public CatFactClass(String fact, int length) {
		super();
		this.fact = fact;
		this.length = length;
	}

	@Override
	public String toString() {
		return "** CatFact [fact=" + fact + ", length=" + length + "]";
	}


}
