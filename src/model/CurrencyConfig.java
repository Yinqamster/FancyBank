package model;

import java.math.BigDecimal;

public class CurrencyConfig {

	private BigDecimal serviceChargeRate;
	private BigDecimal interestsForSavingAccount;
	private BigDecimal interestsForLoan;
	//the lowest balance that can get interest for saving account
	private BigDecimal balanceForInterest;
	
	public BigDecimal getServiceChargeRate() {
		return this.serviceChargeRate;
	}
	
	public void setServiceChargeRate(BigDecimal serviceChargeRate) {
		this.serviceChargeRate = serviceChargeRate;
	}
	
	public BigDecimal getInterestsForSavingAccount() {
		return this.interestsForSavingAccount;
	}
	
	public void setInterestsForSavingAccount(BigDecimal interestsForSavingAccount) {
		this.interestsForSavingAccount = interestsForSavingAccount;
	}
	
	public BigDecimal getInterestsForLoan() {
		return this.interestsForLoan;
	}
	
	public void setInterestForLoan(BigDecimal interestsForLoan) {
		this.interestsForLoan = interestsForLoan;
	}
	
	public BigDecimal getBalanceForInterest() {
		return this.balanceForInterest;
	}
	
	public void setBalanceForInterest(BigDecimal balanceForInterest) {
		this.balanceForInterest = balanceForInterest;
	}
}
