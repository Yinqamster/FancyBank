package model;

public class PhoneNumber {

	private String region;
	private int regionCode;
	private long phoneNumber;
	
	public PhoneNumber() {
		
	}
	
	public PhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public long getPhoneNumber() {
		return this.phoneNumber;
	}
}
