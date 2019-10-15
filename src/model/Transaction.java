package model;

import java.math.BigDecimal;

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
