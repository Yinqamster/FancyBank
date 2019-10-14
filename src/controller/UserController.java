package controller;

import Utils.ErrCode;
import model.Name;

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
//		if()
//		User user = new User(name, phoneNum, email, birthday, password);
//		Config.userList.put(user.getName().getNickName(), user);
//		Utils.printUsers();
		return ErrCode.OK;
	}

	@Override
	public int login(String username, String password) {
		// TODO Auto-generated method stub
		return 0;
	}
}
