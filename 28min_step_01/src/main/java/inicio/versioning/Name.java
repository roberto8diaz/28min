package inicio.versioning;

public class Name {
	private String firstName;
	private String lasttName;
	
	public Name() {
		
	}
	
	public Name(String firstName, String lasttName) {
		super();
		this.firstName = firstName;
		this.lasttName = lasttName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLasttName() {
		return lasttName;
	}
	public void setLasttName(String lasttName) {
		this.lasttName = lasttName;
	}
	
}
