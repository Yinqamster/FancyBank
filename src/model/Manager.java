package model;

import java.math.BigDecimal;

import Utils.Config;

public class Manager extends Person{

	private String password;
	private int status;
	private BigDecimal balance;
	
	public Manager(){
		
	}
	
	public Manager(Name name, int phoneNum, String email, Date birthday, String password) {
		super(name, phoneNum, email, birthday);
		int id = getMaxID() + 1;
		super.setID(id);
		this.password = password;
		this.status = Config.NOTLOGGEDIN;
	}
	
	public int getMaxID() {
		int maxId = 0;
		if(Config.managerList.isEmpty()) {
			return 0;
		}
		for(Manager m : Config.managerList.values()) {
			if(m.getID() > maxId){
				maxId = m.getID();
			}
		}
		return maxId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getStatus() {
		return this.status;
	}
	public void setStatus(int status) {
		this.status = status; 
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	
	public void setBalance(BigDecimal num) {
		this.balance = num;
	}
	
	public void print() {
		System.out.println("================================================");
		System.out.println("User ID: " + this.getID());
		System.out.println("First Name: " + this.getName().getFirstName());
		System.out.println("Middle Name: " + this.getName().getMiddleName());
		System.out.println("Last Name: " + this.getName().getLastName());
		System.out.println("Username Name: " + this.getName().getNickName());
		System.out.println("Phone Number: " + this.getPhoneNumber());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Birthday: " + this.getDob().toString());
		System.out.println("Password: " + password);
		System.out.println("================================================");
	}
}
