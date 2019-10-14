package Utils;

import java.util.HashMap;
import java.util.Map;

import model.Currency;
import model.Manager;
import model.User;

public class Config {

	public static final String USER = "User";
	public static final String MANAGER = "Manager";
	
	//log status
	public static final int NOTLOGGEDIN = 0;
	public static final int LOGGEDIN = 1;
	
	//transaction status
	public static final int CASHIN = 1;
	public static final int CASHOUT = 2;
	public static final int TRANSFER = 3;
	
	//userId, user
	public static Map<String, User> userList = new HashMap<String, User>();
	//managerId, manager
	public static Map<String, Manager> managerList = new HashMap<String, Manager>();
	//currency name, currrency
	public static Map<String, Currency> currencyList = new HashMap<String, Currency>();
	
}
