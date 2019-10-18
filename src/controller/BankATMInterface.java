package controller;

import model.Name;

public interface BankATMInterface {
	public int register(Name name, int sex, String phoneNum, String email, String birthday, String password, String cPassword);
	public int login(String username, String password);
	public int logout(String username);
	
}
