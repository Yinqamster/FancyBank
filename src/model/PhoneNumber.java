package model;

public class PhoneNumber {

	private String region;
	private int regionCode;
	private int phoneNumber;
	
	public PhoneNumber() {
		
	}
	
	public PhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getPhoneNumber() {
		return this.phoneNumber;
	}
}
