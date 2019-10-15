package model;

public class Currency {

	private String currencyName;
	private CurrencyConfig config;
	
	public Currency(){
		
	}
	
	public Currency(String name) {
		this.currencyName = name;
	}
	
	public Currency(String name, CurrencyConfig currencyConfig) {
		this(name);
		this.config = currencyConfig;
	}
	
	public CurrencyConfig getCurrencyConfig() {
		return this.config;
	}
}
