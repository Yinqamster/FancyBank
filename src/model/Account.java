package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Account {

	private int accountType;
	private String accountNumber;
	private Map<String, BigDecimal> balance;
	private List<Transaction> transactionDetails;
	
	public int getAccountType(){
		return this.accountType;
	}
	
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public Map<String, BigDecimal> getBalance() {
		return this.balance;
	}
	
	public void setBalance(Map<String, BigDecimal> balance){
		this.balance = balance;
	}
	
	public List<Transaction> getTransactionDetails() {
		return this.transactionDetails;
	}
	
	public void addTransactionDetails(Transaction t) {
		this.transactionDetails.add(t);
	}
}
