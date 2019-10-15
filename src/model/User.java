package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.Config;
import Utils.UtilFunction;
import controller.BankController;

public class User extends Person{

	private String password;
	private int status;
	
//	private int userID;
	
	private Map<String, Account> accounts;
	
	private List<Loan> loanList;
	
	public User(){
		
	}
	
	public User(Name name, int phoneNum, String email, Date birthday, String password) {
		super(name, phoneNum, email, birthday);
		int id = getMaxID() + 1;
		super.setID(id);
		this.password = password;
		accounts = new HashMap<String, Account>();
		loanList = new ArrayList<Loan>();
	}
	
	public int getMaxID(){
		int maxId = 0;
		if(BankController.getBank().getUserList().isEmpty()) {
			return 0;
		}
		for(User u : BankController.getBank().getUserList().values()) {
			if(u.getID() > maxId){
				maxId = u.getID();
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
	
	public Map<String, Account> getAccounts() {
		return this.accounts;
	}
	
	public void addAccount(String accountNumber, Account account) {
		this.accounts.put(accountNumber, account);
	}
	
	public List<Loan> getLoanList() {
		return this.loanList;
	}
	
	public void addLoan(Loan loan) {
		this.loanList.add(loan);
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
