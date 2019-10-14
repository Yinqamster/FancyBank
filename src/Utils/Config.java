package Utils;

import java.util.HashMap;
import java.util.Map;

import model.Manager;
import model.User;

public class Config {

	public static final String USER = "User";
	public static final String MANAGER = "Manager";
	
	public static final int NOTLOGGEDIN = 0;
	public static final int LOGGEDIN = 1;
	
	//userId, user
	public static Map<String, User> userList = new HashMap<String, User>();
	//managerId, manager
	public static Map<String, Manager> managerList = new HashMap<String, Manager>();
	
}
