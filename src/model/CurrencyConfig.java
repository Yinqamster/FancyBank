package model;

import java.math.BigDecimal;

public class CurrencyConfig {

	private BigDecimal serviceChargeRate;
	private BigDecimal interests;
	
	public BigDecimal getServiceChargeRate() {
		return this.serviceChargeRate;
	}
	
	public BigDecimal getInterests() {
		return this.interests;
	}
}
