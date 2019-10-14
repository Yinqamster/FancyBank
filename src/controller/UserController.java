package controller;

import Utils.Config;
import Utils.ErrCode;
import Utils.UtilFunction;
import model.Manager;
import model.Name;
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
}
