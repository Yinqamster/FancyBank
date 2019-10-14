package model;

import java.math.BigInteger;

public class Transaction {
	private User from;
	private User to;
	private BigInteger num;
	private Date date;
	private int status;
	private String transactionID;
	private String remarks;
}
