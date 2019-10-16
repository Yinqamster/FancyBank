package controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import model.Account;
//import model.Date;
import model.Bank;
import model.Name;
import model.User;
import utils.Config;
import utils.ErrCode;
import utils.UtilFunction;

public class BankController implements BankATMInterface{
	
	private static BankController instance;
	
	private static Bank bank;
	
	private BankController() {
		// TODO Auto-generated constructor stub
	}
	
	public static BankController getInstance() {  
	    if (instance == null) {  
	        instance = new BankController();  
	    }
	    return instance;
	}
	
	public static Bank getBank() {
		if(bank == null) {
			bank = new Bank();
		}
		return bank;
	}
	
	public static Bank createBank() {
		if(bank == null) {
			bank = new Bank();
		}
		handInterest();
		return bank;
	}

	@Override
	public int register(Name name, String phoneNum, String email, String birthday, String password, String cPassword) {
		return ErrCode.OK;
	}

	@Override
	public int login(String username, String password) {
		// TODO Auto-generated method stub
		if(UtilFunction.checkName(username, Config.MANAGER) != ErrCode.OK) {
			return UtilFunction.checkName(username, Config.MANAGER);
		}
		if(UtilFunction.checkPassword(password) != ErrCode.OK) {
			return UtilFunction.checkPassword(password);
		}
		
		if (!password.equals(bank.getPassword())) {
			return ErrCode.WRONGPASSWORD;
		}
		return ErrCode.OK;
	}

	@Override
	public int logout(String username) {
		// TODO Auto-generated method stub
		if(UtilFunction.checkName(username, Config.MANAGER) != ErrCode.OK) {
			return UtilFunction.checkName(username, Config.MANAGER);
		}
		return ErrCode.OK;
	}
	
	public int checkCustomer() {
		
		return ErrCode.OK;
	}
	
	public int getDailyReport() {
		
		return ErrCode.OK;
	}
	
	public int setConfig(String currencyName) {
		
		return ErrCode.OK;
	}
	
	public static void handInterest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
 
        Date time = calendar.getTime();         // execute at 12：00：00
 
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("begin to hand interests");
                if(bank.getUserList().size() > 0) {
	                for(User user : bank.getUserList().values()) {
	                	if(user.getAccounts().size() > 0) {
		                	for(Account account: user.getAccounts().values()) {
		                		if(account.getAccountType() == Config.SAVINGACCOUNT) {
		                			Map<String, BigDecimal> balanceList = account.getBalance();
		                			for(Map.Entry<String, BigDecimal> balance: balanceList.entrySet()) {
		                				BigDecimal balanceForInterest = bank.getCurrencyList().get(balance.getKey()).getCurrencyConfig().getBalanceForInterest();
		                				if(balance.getValue().compareTo(balanceForInterest) >= 0) {
		                					BigDecimal interestsRate = bank.getCurrencyList().get(balance.getKey()).getCurrencyConfig().getInterestsForSavingAccount();
		                					BigDecimal interests = balance.getValue().multiply(interestsRate).divide(new BigDecimal("365"));
		                					balance.setValue(balance.getValue().add(interests));
		                				}
		                			}
		                			account.setBalance(balanceList);
		                		}
		                		user.addAccount(account.getAccountNumber(), account);
		                	}
		                	bank.addUser(user.getName().getNickName(), user);
	                	}
	                }
                }
            }
        }, time, 1000 * 60 * 60 * 24);//execute per day
    }
	
}
