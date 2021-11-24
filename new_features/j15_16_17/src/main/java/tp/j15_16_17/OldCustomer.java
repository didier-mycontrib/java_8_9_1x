package tp.j15_16_17;

public class OldCustomer{
	
	private Integer id;
	private String firstName;
	private String lastName;
	
	
	public OldCustomer() {
		super();
	}


	public OldCustomer(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	@Override
	public String toString() {
		return "OldCustomer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
