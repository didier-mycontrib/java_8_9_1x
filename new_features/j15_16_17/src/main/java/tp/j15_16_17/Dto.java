package tp.j15_16_17;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class Dto {
	
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public record Address(Integer number,String street,String zipCode,String town) {
	};
	
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public record Person(Integer id,String firstName,String lastName,Address address) {
		public Person(Integer id,String firstName,String lastName) {
			this(id,firstName,lastName,null);
		}
	};

}
