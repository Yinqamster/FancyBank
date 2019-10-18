package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import model.Account;
import model.Bank;
import model.Currency;
import model.CurrencyConfig;
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
	
	public static Bank initBank() {
		if(bank == null) {
			bank = new Bank();
		}
		handInterest();
		return bank;
	}

	@Override
	public int register(Name name, int sex, String phoneNum, String email, String birthday, String password, String cPassword) {
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
	
	public Map<String, BigDecimal> getBalance() {
		return bank.getBalance();
	}
	
	public int checkCustomer() {
		
		return ErrCode.OK;
	}
	
	//check customer
	public List<User> getUsersByCondition(String username, int sortBy, int sortOrder) {
		List<User> res = new ArrayList<User>();
		if(username != null && !username.isEmpty()) {
			if(bank.getUserList().containsKey(username)){
				res.add(bank.getUserList().get(username));
			}
			else{
				return null;
			}
		}
		else {
			for(User user : bank.getUserList().values()){
				res.add(user);
			}
		}
		
		res.sort(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				if(sortBy == Config.SORTBYID) {
					if(sortOrder == Config.ASC) {
						if(o1.getID() < o2.getID()) {
							return -1;
						}
						else if(o1.getID() == o2.getID()) {
							return 0;
						}
						else if(o1.getID() > o2.getID()) {
							return 1;
						}
					}
					else if(sortOrder == Config.DESC) {
						if(o1.getID() < o2.getID()) {
							return 1;
						}
						else if(o1.getID() == o2.getID()) {
							return 0;
						}
						else if(o1.getID() > o2.getID()) {
							return -1;
						}
					}
				}
				else if(sortBy == Config.SORTBYLOANSIZE) {
					int o1LoanSize = o1.getLoanList() == null ? 0 : o1.getLoanList().size();
					int o2LoanSize = o2.getLoanList() == null ? 0 : o2.getLoanList().size();
					if(sortOrder == Config.ASC) {
						
						if(o1LoanSize < o2LoanSize) {
							return -1;
						}
						else if(o1LoanSize == o2LoanSize) {
							return 0;
						}
						else if(o1LoanSize > o2LoanSize) {
							return 1;
						}
					}
					else if(sortOrder == Config.DESC) {
						if(o1LoanSize < o2LoanSize) {
							return 1;
						}
						else if(o1LoanSize == o2LoanSize) {
							return 0;
						}
						else if(o1LoanSize > o2LoanSize) {
							return -1;
						}
					}
				}
				else if(sortBy == Config.SORTBYACCOUNTSIZE) {
					int o1AccountSize = o1.getAccounts() == null ? 0 : o1.getAccounts().size();
					int o2AccountSize = o2.getAccounts() == null ? 0 : o2.getAccounts().size();
					if(sortOrder == Config.ASC) {
						
						if(o1AccountSize < o2AccountSize) {
							return -1;
						}
						else if(o1AccountSize == o2AccountSize) {
							return 0;
						}
						else if(o1AccountSize > o2AccountSize) {
							return 1;
						}
					}
					else if(sortOrder == Config.DESC) {
						if(o1AccountSize < o2AccountSize) {
							return 1;
						}
						else if(o1AccountSize == o2AccountSize) {
							return 0;
						}
						else if(o1AccountSize > o2AccountSize) {
							return -1;
						}
					}
				}
				return 0;
			}
			
		});
		
		return res;
	}
	
	
	
	public int getDailyReport() {
		
		return ErrCode.OK;
	}
	
	
	
	//set configuration
	public int setCurrencyStatus(String currency, int status) {
		if(bank.getCurrencyList().containsKey(currency)) {
			bank.getCurrencyList().get(currency).setStatus(status);
		}
		return ErrCode.OK;
	}
	
	public int editCurrency(String currency, String scRate, String ifsAcc, String ifLoan, String bfInterest) {
		if(currency.isEmpty() || currency == null) {
			return ErrCode.CURRENCYISNULL;
		}
		if(!UtilFunction.isNumber(scRate) || !UtilFunction.isNumber(ifsAcc) || !UtilFunction.isNumber(ifLoan) || !UtilFunction.isNumber(bfInterest)){
			return ErrCode.INPUTNOTANUMBER;
		}
		Currency c;
		CurrencyConfig config = new CurrencyConfig(new BigDecimal(scRate), new BigDecimal(ifsAcc),
				new BigDecimal(ifLoan), new BigDecimal(bfInterest));
		if(bank.getCurrencyList().containsKey(currency)){
			c = bank.getCurrencyList().get(currency);
		}
		else {
			c = new Currency();
		}
		c.setConfig(config);
		bank.getCurrencyList().put(currency, c);
		bank.getBalance().put(currency, new BigDecimal("0"));
		return ErrCode.OK;
	}
	
	public int deleteCurrency(String currency) {
		if(bank.getCurrencyList().containsKey(currency)) {
			bank.getCurrencyList().remove(currency);
		}
		return ErrCode.OK;
	}
	
	public int saveConfig(String open, String close) {
		if(!UtilFunction.isNumber(open) || !UtilFunction.isNumber(close)) {
			return ErrCode.INPUTNOTANUMBER;
		}
		bank.setOpenAccountFee(new BigDecimal(open));
		bank.setCloseAccountFee(new BigDecimal(close));
		return ErrCode.OK;
	}
	
	
	
	
	
	
	
	
	//hand interests for saving accounts
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
		                				BigDecimal balanceForInterest = bank.getCurrencyList().get(balance.getKey()).getConfig().getBalanceForInterest();
		                				if(balance.getValue().compareTo(balanceForInterest) >= 0) {
		                					BigDecimal interestsRate = bank.getCurrencyList().get(balance.getKey()).getConfig().getInterestsForSavingAccount();
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
