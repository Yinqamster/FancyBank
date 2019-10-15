package model;

import java.math.BigDecimal;

public class Loan {
	String name;
	BigDecimal number;
	Date startDate;
	Date dueDate;
	String collateral;
	
	public String getName() {
		return this.name;
	}
	
	public BigDecimal getNumber() {
		return this.number;
	}
}
