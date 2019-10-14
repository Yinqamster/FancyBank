package controller;

import Utils.Config;
import Utils.ErrCode;
import Utils.UtilFunction;
import model.Date;
import model.Manager;
import model.Name;
import model.User;

public class ManagerController implements BankATMInterface{
	
	private static ManagerController instance;
	
	private ManagerController() {
		// TODO Auto-generated constructor stub
	}
	
	public static ManagerController getInstance() {  
	    if (instance == null) {  
	        instance = new ManagerController();  
	    }
	    return instance;
	}

	@Override
	public int register(Name name, String phoneNum, String email, String birthday, String password, String cPassword) {
		//TODO check valid
		if(UtilFunction.checkName(name, Config.MANAGER) != ErrCode.OK) {
			return UtilFunction.checkName(name, Config.MANAGER);
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
		Manager manager = new Manager(name, (int)Integer.valueOf(phoneNum), email, UtilFunction.stringToDate(birthday), password);
		Config.managerList.put(manager.getName().getNickName(), manager);
		UtilFunction.printManagers();
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
		
		Manager manager = Config.managerList.get(username);
		if (!password.equals(manager.getPassword())) {
			return ErrCode.WRONGPASSWORD;
		}
		manager.setStatus(Config.LOGGEDIN);
		Config.managerList.put(username, manager);
		return ErrCode.OK;
	}

	@Override
	public int logout(String username) {
		// TODO Auto-generated method stub
		if(UtilFunction.checkName(username, Config.MANAGER) != ErrCode.OK) {
			return UtilFunction.checkName(username, Config.MANAGER);
		}
		Manager manager = Config.managerList.get(username);
		manager.setStatus(Config.NOTLOGGEDIN);
		Config.managerList.put(username, manager);
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
	
	
}
