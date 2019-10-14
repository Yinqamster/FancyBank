package model;

import java.util.HashMap;
import java.util.Map;

import Utils.Config;
import Utils.UtilFunction;

public class User extends Person{

	private String password;
	private int status;
	
//	private int userID;
	
	private Map<Integer, Account> accounts;
	
	public User(){
		
	}
	
	public User(Name name, int phoneNum, String email, Date birthday, String password) {
		super(name, phoneNum, email, birthday);
		int id = getMaxID() + 1;
		super.setID(id);
		this.password = password;
		accounts = new HashMap<Integer, Account>();
	}
	
	public int getMaxID(){
		int maxId = 0;
		if(Config.userList.isEmpty()) {
			return 0;
		}
		for(User u : Config.userList.values()) {
			if(u.getID() > maxId){
				maxId = u.getID();
			}
		}
		return maxId;
	}
	
	public int getStatus() {
		return this.status;
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
