package model;

import java.math.BigInteger;
import java.util.List;

public class Account {

	private int accountType;
	private int accountNumber;
	private BigInteger balance;
	private List<Transaction> transactionDetails;
}
