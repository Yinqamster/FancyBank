package model;

import java.math.BigDecimal;

public class Transaction {
	private int fromAccountNumber;
	private int toAccountNumber;
	private BigDecimal num;
	private Date date;
	private int status;
	private String transactionID;
	private String remarks;
	private int transactionType;
	
	public Transaction() {
		
	}
	
	public Transaction(BigDecimal num, Date date, String remarks, int transactionType) {
		this.num = num;
		this.date = date;
		this.remarks = remarks;
		this.transactionType = transactionType;
		//TODO generate transactionID
//		this.transactionID = 
	}
	
	public Transaction(BigDecimal num, Date date, String remarks, int transactionType, int from, int to) {
		this(num, date, remarks, transactionType);
		this.fromAccountNumber = from;
		this.toAccountNumber = to;
		//TODO generate transactionID
//		this.transactionID = 
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
