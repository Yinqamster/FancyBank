package model;

public class Name {
	//define a person's name
	private String firstName;
	private String middleName;
	private String lastName;
	private String nickName;
	
	public Name() {
		
	}
	
	public Name(String fname, String mname, String lname, String nname) {
		firstName = fname;
		middleName = mname;
		lastName = lname;
		nickName = nname;
	}
	
	public void setFirstName(String fname) {
		firstName = fname;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setMiddleName(String mname) {
		middleName = mname;
	}
	
	public void setLastName(String lname) {
		lastName = lname;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setNickName(String nname) {
		nickName = nname;
	}
	
	public String getNickName() {
		return nickName;
	}
		
}
