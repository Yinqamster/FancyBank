package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Utils.Config;
import Utils.ErrCode;
import Utils.UtilFunction;
import model.Account;
import model.CurrencyConfig;
import model.Name;
import model.Transaction;
import model.User;

public class UserController implements BankATMInterface{
	
	private static UserController instance;
	
	private UserController() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserController getInstance() {  
	    if (instance == null) {  
	        instance = new UserController();  
	    }
	    return instance;
	}

	@Override
	public int register(Name name, String phoneNum, String email, String birthday, String password, String cPassword) {
		//TODO check valid
		if(UtilFunction.checkName(name, Config.USER) != ErrCode.OK) {
			return UtilFunction.checkName(name, Config.USER);
		}
		if(UtilFunction.checkPhoneNumber(phoneNum) != ErrCode.OK) {
			return UtilFunction.checkPhoneNumber(phoneNum);
		}
		if(UtilFunction.checkEmail(email) != ErrCode.OK) {
			return UtilFunction.checkEmail(email);
		}
		if(UtilFunction.checkBirthday(birthday) != ErrCode.OK) {
			return UtilFunction.checkBirthday(birthday);
		}
		if(UtilFunction.checkPassword(password, cPassword) != ErrCode.OK) {
			return UtilFunction.checkPassword(password, cPassword);
		}
		User user = new User(name, (int)Integer.valueOf(phoneNum), email, UtilFunction.stringToDate(birthday), password);
		Config.userList.put(user.getName().getNickName(), user);
		UtilFunction.printUsers();
		return ErrCode.OK;
	}

	@Override
	public int login(String username, String password) {
		// TODO Auto-generated method stub
		if(UtilFunction.checkName(username, Config.USER) != ErrCode.OK) {
			return UtilFunction.checkName(username, Config.USER);
		}
		if(UtilFunction.checkPassword(password) != ErrCode.OK) {
			return UtilFunction.checkPassword(password);
		}
		
		User user = Config.userList.get(username);
		if (!password.equals(user.getPassword())) {
			return ErrCode.WRONGPASSWORD;
		}
		user.setStatus(Config.LOGGEDIN);
		Config.userList.put(username, user);
		return ErrCode.OK;
	}

	@Override
	public int logout(String username) {
		// TODO Auto-generated method stub
		if(UtilFunction.checkName(username, Config.USER) != ErrCode.OK) {
			return UtilFunction.checkName(username, Config.USER);
		}
		User user = Config.userList.get(username);
		user.setStatus(Config.NOTLOGGEDIN);
		Config.userList.put(username, user);
		return ErrCode.OK;
	}
	
	public List<Integer> getAccountList(String username, int accountType) {
		List<Integer> accountList = new ArrayList<Integer>();
		User user = Config.userList.get(username);
		for(Account a: user.getAccounts().values()){
			if(a.getAccountType() == accountType) {
				accountList.add(a.getAccountNumber());
			}
		}
		return accountList;
	}
	
	public Account getAccountDetail(String username, int accountNumber) {
		User user = Config.userList.get(username);
		Account account = user.getAccounts().get(accountNumber);
		return account;
	}
	
	public int cashIn(String username, int accountType, int accountNumber, BigDecimal number, String currency){
		if(!Config.currencyList.containsKey(currency)) {
			return ErrCode.NOSUCHCURRENCY;
		}
		CurrencyConfig currencyConfig = Config.currencyList.get(currency).getCurrencyConfig();
		User user = Config.userList.get(username);
		Account account = user.getAccounts().get(accountNumber);
		if(account.getAccountType() != accountType){
			System.out.println("Wrong account type and account number");
			return ErrCode.ERROR;
		}
		Map<String, BigDecimal> balanceList = account.getBalance();
		BigDecimal oldBalance = new BigDecimal("0");
		if(balanceList.containsKey(currency)){
			oldBalance = balanceList.get(currency);
		}
		BigDecimal serviceCharge = number.multiply(currencyConfig.getServiceChargeRate());
		BigDecimal newBalance = oldBalance.add(number.subtract(serviceCharge));
		//TODO add service charge to manager
		balanceList.put(currency, newBalance);
		account.setBalance(balanceList);
		
		Transaction t = new Transaction(number, UtilFunction.now(), null, Config.CASHIN);
		account.addTransactionDetails(t);
		user.getAccounts().put(accountNumber, account);
		Config.userList.put(username, user);
		return ErrCode.OK;
	}
	
	public int cashOut(String username, int accountType, int accountNumber, BigDecimal number, String currency) {
		if(!Config.currencyList.containsKey(currency)) {
			return ErrCode.NOSUCHCURRENCY;
		}
		CurrencyConfig currencyConfig = Config.currencyList.get(currency).getCurrencyConfig();
		User user = Config.userList.get(username);
		Account account = user.getAccounts().get(accountNumber);
		if(account.getAccountType() != accountType){
			System.out.println("Wrong account type and account number");
			return ErrCode.ERROR;
		}
		Map<String, BigDecimal> balanceList = account.getBalance();
		
		if(!balanceList.containsKey(currency)){
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal oldBalance = balanceList.get(currency);
		if(oldBalance.compareTo(number) < 0) {
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal serviceCharge = number.multiply(currencyConfig.getServiceChargeRate());
		BigDecimal newBalance = oldBalance.subtract(number).subtract(serviceCharge);
		//TODO add service charge to manager
		balanceList.put(currency, newBalance);
		account.setBalance(balanceList);
		Transaction t = new Transaction(number, UtilFunction.now(), null, Config.CASHOUT);
		account.addTransactionDetails(t);
		user.getAccounts().put(accountNumber, account);
		Config.userList.put(username, user);
		return ErrCode.OK;
	}
	
	public int transfer(String username, int accountType, int accountNumber, BigDecimal number, String currency) {
		//TODO to account add number
		if(!Config.currencyList.containsKey(currency)) {
			return ErrCode.NOSUCHCURRENCY;
		}
		CurrencyConfig currencyConfig = Config.currencyList.get(currency).getCurrencyConfig();
		User user = Config.userList.get(username);
		Account account = user.getAccounts().get(accountNumber);
		if(account.getAccountType() != accountType){
			System.out.println("Wrong account type and account number");
			return ErrCode.ERROR;
		}
		Map<String, BigDecimal> balanceList = account.getBalance();
		
		if(!balanceList.containsKey(currency)){
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal oldBalance = balanceList.get(currency);
		if(oldBalance.compareTo(number) < 0) {
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal serviceCharge = number.multiply(currencyConfig.getServiceChargeRate());
		BigDecimal newBalance = oldBalance.subtract(number).subtract(serviceCharge);
		//TODO add service charge to manager
		balanceList.put(currency, newBalance);
		account.setBalance(balanceList);
		Transaction t = new Transaction(number, UtilFunction.now(), null, Config.TRANSFER);
		account.addTransactionDetails(t);
		user.getAccounts().put(accountNumber, account);
		Config.userList.put(username, user);
		return ErrCode.OK;
	}
	
	public int createAccount(String username, int accountType) {
		Account account = new Account();
		account.setAccountType(accountType);
		//TODO generate account number
//		account.setAccountNumber(accountNumber);
		User user = Config.userList.get(username);
		user.getAccounts().put(account.getAccountNumber(), account);
		return ErrCode.OK;
	}
}
