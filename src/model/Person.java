package model;

public class Person {
	//define a normal person with name
	private Name name;
	private PhoneNumber phoneNumber;
	private String email;
	private Date dob;
	private int ID;
	
	public Person() {}
	public Person(Name name, int phoneNumber, String email, Date dob) {
		this.name = name;
		this.phoneNumber = new PhoneNumber(phoneNumber);
		this.email = email;
		this.dob = dob;
	}
	
	public Name getName() {
		return this.name;
	}
	
	public void setName(Name name) {
		this.name = name;
	}
	
	public int getPhoneNumber() {
		return phoneNumber.getPhoneNumber();
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = new PhoneNumber(phoneNumber);
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDob() {
		return this.dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
}
