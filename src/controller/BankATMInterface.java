package controller;

import model.Date;
import model.Name;

public interface BankATMInterface {
	public int register(Name name, String phoneNum, String email, String birthday, String password, String cPassword);
	public int login(String username, String password);
	
}
