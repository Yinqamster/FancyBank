package model;

import java.math.BigDecimal;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import Utils.Config;
import Utils.UtilFunction;

public class Transaction {
	private int fromAccountNumber;
	private int toAccountNumber;
	private BigDecimal num;
	private Date date;
	private int status;
	private String transactionID;
	private String remarks;
	private int transactionType;
	private BigDecimal serviceCharge;
	private BigDecimal balance;
	
	public Transaction() {
		this.transactionID = UtilFunction.generateTransactionID();
	}
	
	public Transaction(BigDecimal num, BigDecimal serviceCharge, BigDecimal balance,
			Date date, String remarks, int transactionType) {
		this();
		this.num = num;
		this.serviceCharge = serviceCharge;
		this.balance = balance;
		this.date = date;
		this.remarks = remarks;
		this.transactionType = transactionType;
		
	}
	
	public Transaction(BigDecimal num, BigDecimal serviceCharge, BigDecimal balance, 
			Date date, String remarks, int transactionType, int from, int to) {
		this(num, serviceCharge, balance, date, remarks, transactionType);
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
	
	public String getTransactionId() {
		return this.transactionID;
	}
	
	public int getFromAccountNumber() {
		return this.fromAccountNumber;
	}
	
	public void setFromAccountNumber(int from) {
		this.fromAccountNumber = from;
	}
	
	public int getToAccountNumber() {
		return this.toAccountNumber;
	}
	
	public void setToAccountNumber(int to) {
		this.toAccountNumber = to;
	}
	
	public BigDecimal getNum() {
		return this.num;
	}
	
	public void setNum(BigDecimal num) {
		this.num = num;
	}
}
