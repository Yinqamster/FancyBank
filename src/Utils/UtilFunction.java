package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Date;
import model.Manager;
import model.Name;
import model.User;

public class UtilFunction {

	public static void printUsers() {
		for(User user : Config.userList.values()) {
			user.print();
		}
	}
	
	public static void printManagers() {
		for(Manager manager : Config.managerList.values()) {
			manager.print();
		}
	}
	
	//login check
	public static int checkName(String username, String identity) {
		if(username.isEmpty() || username == null) {
			return ErrCode.MISSUSERNAME;
		}
		if(identity == Config.USER && !Config.userList.containsKey(username)) {
			return ErrCode.USERNAMENOTEXISTS;
		}
		if(identity == Config.MANAGER && !Config.managerList.containsKey(username)) {
			return ErrCode.USERNAMENOTEXISTS;
		}
		return ErrCode.OK;
	}
	
	//register check
	public static int checkName(Name name, String identity) {
		if(name.getFirstName().isEmpty() || name.getFirstName() == null) {
			return ErrCode.MISSFIRSTNAME;
		}
		if(name.getLastName().isEmpty() || name.getLastName() == null) {
			return ErrCode.MISSLASTNAME;
		}
		if(name.getNickName().isEmpty() || name.getNickName() == null) {
			return ErrCode.MISSUSERNAME;
		}
		if(identity == Config.USER && Config.userList.containsKey(name.getNickName())) {
			return ErrCode.USERNAMEEXISTS;
		}
		if(identity == Config.MANAGER && Config.managerList.containsKey(name.getNickName())) {
			return ErrCode.USERNAMEEXISTS;
		}
		return ErrCode.OK;
	}
	
	public static int checkPhoneNumber(String phoneNumber) {
		if(phoneNumber.isEmpty() || phoneNumber == null) {
			return ErrCode.MISSPHONENUMBER;
		}
		if(!isNumber(phoneNumber)) {
			return ErrCode.PHONENUMBERNOTNUMBER;
		}
		return ErrCode.OK;
	}
	
	public static int checkEmail(String email) {
		if(email.isEmpty() || email == null) {
			return ErrCode.MISSEMAIL;
		}
		Pattern pattern = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
		Matcher isEmail = pattern.matcher(email);
		if(!isEmail.matches()) {
			return ErrCode.WRONGEMAILFORMAT;
		}
		return ErrCode.OK;
	}
	
	public static int checkBirthday(String birthday) {
		if(birthday.isEmpty() || birthday == null) {
			return ErrCode.MISSBIRTHDAY;
		}
		String[] date = birthday.split("/");
		if(date.length != 3) {
			return ErrCode.WRONGBIRTHDAYFORMAT;
		}
		String month = date[0];
		String day = date[1];
		String year = date[2];
		if(!isNumber(year)) {
			return ErrCode.WRONGDOBYEAR;
		}
		if(!isNumber(month) || 
				!(Integer.valueOf(month).compareTo(1)>=0 
				&& Integer.valueOf(month).compareTo(12)<=0)) {
			return ErrCode.WRONGDOBMONTH;
		}
		if(!isNumber(day)) {
			return ErrCode.WRONGDOBDAY;
		}
		int d = Integer.valueOf(day);
		int y = Integer.valueOf(year);
		int m = Integer.valueOf(month);
		
		if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12){
			if(!(d >= 1 && d <= 31)) {
				return ErrCode.WRONGDOBDAY;
			}
		}
		else if(m == 4 || m == 6 || m == 9 || m == 11) {
			if(!(d >= 1 && d <= 30)) {
				return ErrCode.WRONGDOBDAY;
			}
		}
		else if(m == 2) {
			if((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
				if(!(d >= 1 && d <= 29)) {
					return ErrCode.WRONGDOBDAY;
				}
			}
			else {
				if(!(d >= 1 && d <= 28)) {
					return ErrCode.WRONGDOBDAY;
				}
			}
		}
		return ErrCode.OK;
	}
	
	public static Date stringToDate(String str) {
		String[] date = str.split("/");
		Date d = new Date((int)Integer.valueOf(date[0]), (int)Integer.valueOf(date[1]), (int)Integer.valueOf(date[2]));
		return d;
	}
	
	public static int checkPassword(String password){
		if(password.isEmpty() || password == null) {
			return ErrCode.MISSPASSWORD;
		}
		return ErrCode.OK;
	}
	
	public static int checkPassword(String password, String cPassword){
		if(password.isEmpty() || password == null) {
			return ErrCode.MISSPASSWORD;
		}
		if(cPassword.isEmpty() || cPassword == null) {
			return ErrCode.MISSCOMFIRMPASSWORD;
		}
		if(!password.equals(cPassword)) {
			return ErrCode.WRONGCOMFIRMPASSWORD;
		}
		return ErrCode.OK;
	}
	
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
	}
	
	
	
	
}
