package model;

import java.math.BigDecimal;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import utils.Config;
import utils.UtilFunction;

public class Transaction {
	private String fromAccountNumber;
	private String toAccountNumber;
	private BigDecimal num;
	private Date date;
	private int status;
	private String transactionID;
	private String remarks;
	private int transactionType;
	private BigDecimal serviceCharge;
	private BigDecimal balance;
	private String currency;
	
	public Transaction() {
		this.transactionID = UtilFunction.generateTransactionID();
	}
	
	public Transaction(String currency, BigDecimal num, BigDecimal serviceCharge, BigDecimal balance,
			Date date, String remarks, int transactionType) {
		this();
		this.currency = currency;
		this.num = num;
		this.serviceCharge = serviceCharge;
		this.balance = balance;
		this.date = date;
		this.remarks = remarks;
		this.transactionType = transactionType;
		
	}
	
	public Transaction(String currency, BigDecimal num, BigDecimal serviceCharge, BigDecimal balance, 
			Date date, String remarks, int transactionType, String from, String to) {
		this(currency, num, serviceCharge, balance, date, remarks, transactionType);
		this.fromAccountNumber = from;
		this.toAccountNumber = to;
	}
	
	public int getTransactionType(){
		return this.transactionType;
	}
	
	public String getTransactionTypeStr(){
		switch (this.transactionType) {
			case Config.DEPOSIT:
				return "Deposit";
			case Config.WITHDRAW:
				return "Withdraw";
			case Config.TRANSFEROUT:
				return "Transfer Out";
			case Config.RECEIVE:
				return "Receive";
			case Config.OPENACCOUNT:
				return "Open Account";
			case Config.CLOSEACCOUNT:
				return "Close Account";
			case Config.PAYFORLOAN:
				return "Pay For Loan";
			default:
				return "";
		}
	}
	
	public String getCurrency() {
		return this.currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getTransactionId() {
		return this.transactionID;
	}
	
	public String getFromAccountNumber() {
		return this.fromAccountNumber;
	}
	
	public BigDecimal getServiceCharge() {
		return this.serviceCharge;
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public String getRemarks(){
		return this.remarks;
	}
	
	public void setFromAccountNumber(String from) {
		this.fromAccountNumber = from;
	}
	
	public String getToAccountNumber() {
		return this.toAccountNumber;
	}
	
	public void setToAccountNumber(String to) {
		this.toAccountNumber = to;
	}
	
	public BigDecimal getNum() {
		return this.num;
	}
	
	public void setNum(BigDecimal num) {
		this.num = num;
	}
}
