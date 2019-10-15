package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.Config;


public class Bank{

	private String username = Config.MANAGERUSERNAME;
	private String password = Config.MANAGERPASSWORD;
	private BigDecimal balance;
	private BigDecimal closeAccountFee;
	private BigDecimal openAccountFee;
	
	//userId, user
	private Map<String, User> userList = new HashMap<String, User>();
	//currency name, currrency
	private Map<String, Currency> currencyList = new HashMap<String, Currency>();
	
	private Map<String, String> accountList = new HashMap<String, String>();
	private List<String> transactionIdList = new ArrayList<String>();
	
	public Bank(){
		balance = new BigDecimal("0");
		openAccountFee = Config.DEFAULTOPENACCOUNTFEE;
		closeAccountFee = Config.DEFAULTCLOSEACCOUNTFEE;
		
		CurrencyConfig currencyConfig  = new CurrencyConfig(
				Config.DEFAULTSERVICECHARGERATE,
				Config.DEFAULTINTERESTFORLOAN,
				Config.DEFAULTINTERESTSFORSAVINGACCOUNT,
				Config.DEFAULTBALANCEFORINTEREST);
		currencyList.put(Config.DEFAULTCURRENCY, new Currency(Config.DEFAULTCURRENCY, currencyConfig));
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	
	public void setBalance(BigDecimal num) {
		this.balance = num;
	}
	
	public Map<String, User> getUserList() {
		return this.userList;
	}
	
	public void addUser(String username, User user) {
		userList.put(username, user);
	}
	
	public Map<String, Currency> getCurrencyList() {
		return this.currencyList;
	}
	
	public void addCurrency(String currencyName, Currency currency) {
		currencyList.put(currencyName, currency);
	}
	
	public Map<String, String> getAccountList() {
		return this.accountList;
	}
	
	public void addAccount(String accountNumber, String username) {
		this.accountList.put(accountNumber, username);
	}
	
	public List<String> getTransactionIdList() {
		return this.transactionIdList;
	}
	
	public void addTransactionId(String transactionId) {
		this.transactionIdList.add(transactionId);
	}
	
	public BigDecimal getOpenAccountFee() {
		return this.openAccountFee;
	}
	
	public BigDecimal getCloseAccountFee() {
		return this.closeAccountFee;
	}
	
	
	
	public void print() {
		System.out.println("================================================");
//		System.out.println("User ID: " + this.getID());
//		System.out.println("First Name: " + this.getName().getFirstName());
//		System.out.println("Middle Name: " + this.getName().getMiddleName());
//		System.out.println("Last Name: " + this.getName().getLastName());
//		System.out.println("Username Name: " + this.getName().getNickName());
//		System.out.println("Phone Number: " + this.getPhoneNumber());
//		System.out.println("Email: " + this.getEmail());
//		System.out.println("Birthday: " + this.getDob().toString());
//		System.out.println("Password: " + password);
		System.out.println("================================================");
	}
}
