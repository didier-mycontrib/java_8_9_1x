package tp.j15_16_17;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TestRecordApp {

	public static void main(String[] args) {
		//testRecord();
		testUsefulRecordV1();
		testUsefulRecordV2();
		testUsefulRecordV3();
		//testJsonWithNullOrEmpty(); //AVEC ERREUR/RESTRICTION A CONNAITRE !!!!
	}
	
	public static void testRecord() {
		
		OldCustomer oldC1 = new OldCustomer(1,"jean","Bon");
		System.out.println("oldC1 as POJO ="+oldC1.toString());
		System.out.println("id of oldC1="+oldC1.getId());
		oldC1.setFirstName("luc");
		System.out.println("firstName of oldC1="+oldC1.getFirstName());
		
		OldCustomer oldC2 = new OldCustomer();
		System.out.println("oldC2="+oldC2.toString());
		
		System.out.println("------------------------------");
		
		LombokCustomer lC1 = new LombokCustomer(1,"jean","Bon");
		System.out.println("olC1 as Lombok POJO ="+lC1.toString());
		System.out.println("id of lC1="+lC1.getId());
		lC1.setFirstName("luc");
		System.out.println("firstName of lC1="+lC1.getFirstName());
		
		LombokCustomer lC2 = new LombokCustomer();
		System.out.println("lC2="+lC2.toString());
		
		System.out.println("------------------------------");
		
		CustomerRecord c1 = new CustomerRecord(1,"jean","Bon"); //ok v1 et v2
		System.out.println("c1 as record ="+c1.toString());
		System.out.println("id of c1="+c1.id());
		System.out.println("firstName of c1="+c1.firstName());
		System.out.println("lastName of c1="+c1.lastName());
		System.out.println("firstName of c1="+c1.fullName());//ok v3
		
		CustomerRecord c2 = new CustomerRecord(); //ok only with v2 (with explicit default constructor)
		System.out.println("c2="+c2.toString());//CustomerRecord[id=0, firstName=null, lastName=null]
	}
	
	//private or public locally RECORD seems to be a good use case for "record" new concept :
	
	private record AddressV1(Integer number,String street,String zipCode,String town) {
		public String toJsonString() {
			return """
					{ 
					  "number" : %d, 
					  "street" : "%s",
					  "zipCode" : "%s",
					  "town" : "%s"
					}
					""".formatted(number,street,zipCode,town);
		}
	};
	
	
	public static void testUsefulRecordV1() {
		AddressV1 a1 = new AddressV1(12,"rueElle","75000" ,"Paris");
		System.out.println("a1="+a1.toString());
		String a1AsJsonString =a1.toJsonString();
		System.out.println("a1="+a1AsJsonString);
		//et dans le sens inverse jsonString --> record a2:
		try {
			ObjectMapper jacksonObjectMapper = new ObjectMapper();
			AddressV1 a2 = jacksonObjectMapper.readValue(a1AsJsonString,AddressV1.class);
			System.out.println("via jackson, a2 (clone de a1)="+a2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	//V2 avec compatibilité avec api jackson-databind (souvent utilisé par JEE , Spring, ...)
	//pratique pour définition de DTO/VO (à la volée)
	//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)//plus nécessaire depuis version 2.12
	public record AddressV2(Integer number,String street,String zipCode,String town) {
	};
	
	public static void testUsefulRecordV2() {
		AddressV2 a1 = new AddressV2(12,"rueElle","75000" ,"Paris");
		System.out.println("a1="+a1.toString());
		try {
			ObjectMapper jacksonObjectMapper = new ObjectMapper();
			String a1JsonString = jacksonObjectMapper.writeValueAsString(a1);
			System.out.println("via jackson, a1JsonString="+a1JsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public static void testUsefulRecordV3() {
		Dto.Person p1Dto = new Dto.Person(1,"jean","Bon" ,
				                          new Dto.Address(12,"rueElle","75000" ,"Paris"));
		System.out.println("\np1Dto="+p1Dto.toString());
		
		try {
			ObjectMapper jacksonObjectMapper = new ObjectMapper();
			String p1JsonString = jacksonObjectMapper.writeValueAsString(p1Dto);
			System.out.println("via jackson, p1JsonString="+p1JsonString);
			//-----
			Dto.Person p1BisDto =jacksonObjectMapper.readValue(p1JsonString,Dto.Person.class);
			System.out.println("via jackson, p1BisDto=clone de p1Dto="+p1BisDto.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public static void testJsonWithNullOrEmpty() {
		Dto.Rxy rXy = new Dto.Rxy(1, null, Optional.of("ok"));
		//Dto.Rxy rXy = new Dto.Rxy(1, null, Optional.empty());
        System.out.println("\nrXy="+rXy.toString());
		
		try {
			ObjectMapper jacksonObjectMapper = new ObjectMapper();
			String rXyJsonString = jacksonObjectMapper.writeValueAsString(rXy);
			System.out.println("via jackson, rXyJsonString="+rXyJsonString);
			//-----
			Dto.Rxy rXyBis =jacksonObjectMapper.readValue(rXyJsonString,Dto.Rxy.class);
			System.out.println("via jackson, rXyBis=clone de rXy="+rXyBis.toString());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//ATTENTION: ça ne fonctionne pas bien du tout
		//bien qu'il existe une extension de jackson-databind sur les types du jdk8 (dont Optional<>)
		//IL EST TRES DECONSEILLE d'avoir des proprietes de type Optional<T>
		//The JSR-335 EG felt fairly strongly that Optional 
		//should not be on any more than needed to support the optional-return idiom only.
		//Someone suggested maybe even renaming it to OptionalReturn

	}
    

}
