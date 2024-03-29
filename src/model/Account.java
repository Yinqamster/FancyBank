/**
* @author Qi Yin
* @ID U31787103
* @description  This is the data structure for account
*/
package model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Account {

//	private int accountType;
	private String accountNumber;
	private Map<String, BigDecimal> balance;
	private Map<String, Transaction> transactionDetails;
	
	public Account() {
		// TODO Auto-generated constructor stub
		//currency, amount
		balance = new HashMap<String, BigDecimal>();
		//transaction id, transaction
		transactionDetails = new HashMap<String, Transaction>();
	}
	
//	public int getAccountType(){
//		return this.accountType;
//	}
//	
//	public void setAccountType(int accountType) {
//		this.accountType = accountType;
//	}
	
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
	
	public Map<String, Transaction> getTransactionDetails() {
		return this.transactionDetails;
	}
	
	public void addTransactionDetails(Transaction t) {
		this.transactionDetails.put(t.getTransactionId(), t);
	}
}
