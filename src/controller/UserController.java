package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Map;

import Utils.Config;
import Utils.ErrCode;
import Utils.UtilFunction;
import model.Account;
import model.Bank;
import model.CurrencyConfig;
import model.Date;
import model.Loan;
import model.Name;
import model.Transaction;
import model.User;

public class UserController implements BankATMInterface{
	
	public BankController bankController = BankController.getInstance();
	public Bank bank = BankController.getBank();
	
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
		if(UtilFunction.checkName(name, Config.USER) != ErrCode.OK) {
			return UtilFunction.checkName(name, Config.USER);
		}
		if(UtilFunction.checkPhoneNumber(phoneNum) != ErrCode.OK) {
			return UtilFunction.checkPhoneNumber(phoneNum);
		}
		if(UtilFunction.checkEmail(email) != ErrCode.OK) {
			return UtilFunction.checkEmail(email);
		}
		if(UtilFunction.checkDate(birthday) != ErrCode.OK) {
			return UtilFunction.checkDate(birthday);
		}
		if(UtilFunction.checkPassword(password, cPassword) != ErrCode.OK) {
			return UtilFunction.checkPassword(password, cPassword);
		}
		User user = new User(name, (int)Integer.valueOf(phoneNum), email, UtilFunction.stringToDate(birthday), password);
		bank.addUser(user.getName().getNickName(), user);
		UtilFunction.printUsers();
		return ErrCode.OK;
	}

	@Override
	public int login(String username, String password) {
		if(UtilFunction.checkName(username, Config.USER) != ErrCode.OK) {
			return UtilFunction.checkName(username, Config.USER);
		}
		if(UtilFunction.checkPassword(password) != ErrCode.OK) {
			return UtilFunction.checkPassword(password);
		}
		
		User user = bank.getUserList().get(username);
		if (!password.equals(user.getPassword())) {
			return ErrCode.WRONGPASSWORD;
		}
		user.setStatus(Config.LOGGEDIN);
		bank.addUser(username, user);
		return ErrCode.OK;
	}

	@Override
	public int logout(String username) {
		// TODO Auto-generated method stub
		if(UtilFunction.checkName(username, Config.USER) != ErrCode.OK) {
			return UtilFunction.checkName(username, Config.USER);
		}
		User user = bank.getUserList().get(username);
		user.setStatus(Config.NOTLOGGEDIN);
		bank.addUser(username, user);
		return ErrCode.OK;
	}
	
	public List<String> getAccountList(String username, int accountType) {
		List<String> accountList = new ArrayList<String>();
		User user = bank.getUserList().get(username);
		for(Account a: user.getAccounts().values()){
			if(a.getAccountType() == accountType) {
				accountList.add(a.getAccountNumber());
			}
		}
		return accountList;
	}
	
	public Account getAccountDetail(String username, String accountNumber) {
		User user = bank.getUserList().get(username);
		Account account = user.getAccounts().get(accountNumber);
		return account;
	}
	
	public int deposit(String username, int accountType, String accountNumber, BigDecimal number, String currency, String remarks){
		if(!bank.getCurrencyList().containsKey(currency)) {
			return ErrCode.NOSUCHCURRENCY;
		}
		CurrencyConfig currencyConfig = bank.getCurrencyList().get(currency).getCurrencyConfig();
		User user = bank.getUserList().get(username);
		if(!bank.getAccountList().containsKey(accountNumber)) {
			return ErrCode.NOSUCHACCOUNT;
		}
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
		bank.setBalance(bank.getBalance().add(serviceCharge));
		balanceList.put(currency, newBalance);
		account.setBalance(balanceList);
		
		Transaction t = new Transaction(number, serviceCharge, newBalance, UtilFunction.now(), remarks, Config.DEPOSIT);
		account.addTransactionDetails(t);
		user.getAccounts().put(accountNumber, account);
		bank.addUser(username, user);
		return ErrCode.OK;
	}
	
	public int withdraw(String username, int accountType, String accountNumber, BigDecimal number, String currency, String remarks) {
		if(!bank.getCurrencyList().containsKey(currency)) {
			return ErrCode.NOSUCHCURRENCY;
		}
		CurrencyConfig currencyConfig = bank.getCurrencyList().get(currency).getCurrencyConfig();
		User user = bank.getUserList().get(username);
		if(!bank.getAccountList().containsKey(accountNumber)) {
			return ErrCode.NOSUCHACCOUNT;
		}
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
		BigDecimal serviceCharge = number.multiply(currencyConfig.getServiceChargeRate());
		
		//balance must bigger than withdraw number add service charge
		if(oldBalance.compareTo(number.add(serviceCharge)) < 0) {
			return ErrCode.NOENOUGHMONEY;
		}
		
		BigDecimal newBalance = oldBalance.subtract(number).subtract(serviceCharge);
		bank.setBalance(bank.getBalance().add(serviceCharge));
		balanceList.put(currency, newBalance);
		account.setBalance(balanceList);
		Transaction t = new Transaction(number, serviceCharge, newBalance, UtilFunction.now(), null, Config.WITHDRAW);
		account.addTransactionDetails(t);
		user.getAccounts().put(accountNumber, account);
		bank.addUser(username, user);
		return ErrCode.OK;
	}
	
	public int transfer(String username, int accountType, String fromAccountNumber, 
			String toAccountNumber, BigDecimal number, String currency, String remarks) {
		if(!bank.getCurrencyList().containsKey(currency)) {
			return ErrCode.NOSUCHCURRENCY;
		}
		CurrencyConfig currencyConfig = bank.getCurrencyList().get(currency).getCurrencyConfig();
		User user = bank.getUserList().get(username);
		if(!bank.getAccountList().containsKey(toAccountNumber) || !bank.getAccountList().containsKey(fromAccountNumber)) {
			return ErrCode.NOSUCHACCOUNT;
		}
		
		Account fromAccount = user.getAccounts().get(fromAccountNumber);
		if(fromAccount.getAccountType() != accountType){
			System.out.println("Wrong account type and account number");
			return ErrCode.ERROR;
		}
		Map<String, BigDecimal> fromBalanceList = fromAccount.getBalance();
		if(!fromBalanceList.containsKey(currency)){
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal oldBalance = fromBalanceList.get(currency);
		BigDecimal serviceCharge = number.multiply(currencyConfig.getServiceChargeRate());
		if(oldBalance.compareTo(number.add(serviceCharge)) < 0) {
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal newBalance = oldBalance.subtract(number).subtract(serviceCharge);
		bank.setBalance(bank.getBalance().add(serviceCharge));
		fromBalanceList.put(currency, newBalance);
		fromAccount.setBalance(fromBalanceList);
		Transaction t = new Transaction(number, serviceCharge, newBalance, UtilFunction.now(), remarks, Config.TRANSFEROUT);
		fromAccount.addTransactionDetails(t);
		user.getAccounts().put(fromAccountNumber, fromAccount);
		bank.addUser(username, user);
		
		User toUser = bank.getUserList().get(bank.getAccountList().get(toAccountNumber));
		Account toAccount = toUser.getAccounts().get(toAccountNumber);
		Map<String, BigDecimal> toBalanceList = toAccount.getBalance();
		BigDecimal toOldBalance = new BigDecimal("0");
		if(!toBalanceList.containsKey(currency)){
			toOldBalance = toBalanceList.get(currency);
		}
		BigDecimal toNewBalance = toOldBalance.add(number);
		toBalanceList.put(currency, newBalance);
		toAccount.setBalance(toBalanceList);
		Transaction toT = new Transaction(number, BigDecimal.ZERO, toNewBalance, UtilFunction.now(), remarks, Config.RECEIVE);
		toAccount.addTransactionDetails(toT);
		toUser.getAccounts().put(toAccountNumber, toAccount);
		bank.addUser(toUser.getName().getNickName(), toUser);
		
		return ErrCode.OK;
	}
	
	
	//default deposit some money when open account
	public int createAccount(String username, int accountType, String currency, BigDecimal number) {
		Account account = new Account();
		account.setAccountType(accountType);
		String accountNumber = UtilFunction.generateAccountNumber();
		bank.addAccount(accountNumber, username);
		account.setAccountNumber(accountNumber);
		User user = bank.getUserList().get(username);
		
		if(!bank.getCurrencyList().containsKey(currency)) {
			return ErrCode.NOSUCHCURRENCY;
		}
		
		Map<String, BigDecimal> balanceList = account.getBalance();
		BigDecimal oldBalance = new BigDecimal("0");
		BigDecimal serviceCharge = bank.getOpenAccountFee();
		if(number.compareTo(serviceCharge) < 0) {
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal newBalance = oldBalance.add(number.subtract(serviceCharge));
		bank.setBalance(bank.getBalance().add(serviceCharge));
		balanceList.put(currency, newBalance);
		account.setBalance(balanceList);
		
		Transaction t = new Transaction(number, serviceCharge, newBalance, UtilFunction.now(), null, Config.OPENACCOUNT);
		account.addTransactionDetails(t);
		user.getAccounts().put(accountNumber, account);
		bank.addUser(username, user);
		return ErrCode.OK;
	}
	
	public int closeAccount(String username, int accountType, String accountNumber) {
		User user = bank.getUserList().get(username);
		Account account = user.getAccounts().get(accountNumber);
		Map<String, BigDecimal> balanceList = account.getBalance();
		BigDecimal serviceCharge = bank.getCloseAccountFee();
		if(!balanceList.containsKey(Config.DEFAULTCURRENCY) 
				|| balanceList.get(Config.DEFAULTCURRENCY).compareTo(serviceCharge) < 0) {
			return ErrCode.NOENOUGHMONEY;
		}
		bank.setBalance(bank.getBalance().add(serviceCharge));
		user.getAccounts().remove(accountNumber);
		bank.getAccountList().remove(accountNumber);
		bank.addUser(username, user);
		return ErrCode.OK;
	}
	
	public String getTruenameByUsername(String username) {
		String name = "";
		Name uname = bank.getUserList().get(username).getName();
		name += uname.getFirstName();
		if(!uname.getMiddleName().isEmpty() && uname.getMiddleName() != null) {
			name += " " + uname.getMiddleName();
		}
		name += " " + uname.getLastName();
		return name;
	}
	
	public Map<String, Loan> getLoanList(String username) {
		return bank.getUserList().get(username).getLoanList();
	}
	
	public int takeLoan(String username, String name, String collateral, String currency, BigDecimal number, String dueDate) {
		User user = bank.getUserList().get(username);
		
		if(name.isEmpty() || name == null) {
			return ErrCode.LOANNAMEEMPTY;
		}
		if(user.getLoanList().containsKey(name)) {
			return ErrCode.LOANNAMEEXIST;
		}
		if(collateral.isEmpty() || collateral == null) {
			return ErrCode.COLLATERALEMPTY;
		}
		if(number.equals(BigDecimal.ZERO) || number == null) {
			return ErrCode.LOANNUMBEREMPTY;
		}
		if(UtilFunction.checkDate(dueDate) != ErrCode.OK) {
			return UtilFunction.checkDate(dueDate);
		}
		
		Date startDate = UtilFunction.now();
		Date endDate = UtilFunction.stringToDate(dueDate);
		Loan loan = new Loan(name, collateral, currency, number, startDate, endDate, Config.PROCESSING);
		user.addLoan(loan);
		bank.addUser(username, user);
		
		return ErrCode.OK;
	}
	
	public int payForLoan(String username, String loanName, String accountNumber) {
		User user = bank.getUserList().get(username);
		if(loanName.isEmpty() || loanName == null) {
			return ErrCode.LOANNAMEEMPTY;
		}
		if(!user.getLoanList().containsKey(loanName)) {
			return ErrCode.LOANNAMENOTEXIST;
		}
		if(user.getLoanList().get(loanName).getStatus() == Config.PAIED 
				|| user.getLoanList().get(loanName).getStatus() == Config.PROCESSING) {
			return ErrCode.LOANCANNOTBEPAIED;
		}
		Loan loan = user.getLoanList().get(loanName);
		if(!user.getAccounts().containsKey(accountNumber)) {
			return ErrCode.NOSUCHACCOUNT;
		}
		int days = UtilFunction.calculateTimeDifference(loan.getStartDate(), loan.getDueDate());
		BigDecimal interestRate = bank.getCurrencyList().get(loan.getCurrency()).getCurrencyConfig().getInterestsForLoan();
		BigDecimal interestsForLoan = loan.getNumber().multiply(interestRate).multiply(new BigDecimal(String.valueOf(days))).divide(new BigDecimal("365"));
		BigDecimal oldBalance = user.getAccounts().get(accountNumber).getBalance().get(loan.getCurrency());
		if(!user.getAccounts().get(accountNumber).getBalance().containsKey(loan.getCurrency())
				|| oldBalance.compareTo(loan.getNumber().add(interestsForLoan))<0) {
			return ErrCode.NOENOUGHMONEY;
		}
		BigDecimal newBalance = oldBalance.subtract(loan.getNumber()).subtract(interestsForLoan);
		user.getAccounts().get(accountNumber).getBalance().put(loan.getCurrency(), newBalance);
		Transaction transaction = new Transaction(loan.getNumber(), interestsForLoan, newBalance, UtilFunction.now(), null, Config.PAYFORLOAN);
		user.getAccounts().get(accountNumber).addTransactionDetails(transaction);
		bank.setBalance(bank.getBalance().add(interestsForLoan));
		loan.setStatus(Config.PAIED);
		user.getLoanList().put(loanName, loan);
		return ErrCode.OK;
	}
}
