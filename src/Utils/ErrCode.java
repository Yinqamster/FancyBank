package Utils;

public class ErrCode {

	public static int ERROR = 0;
	public static int OK = 1;
	
	public static int MISSFIRSTNAME = 101;
	public static int MISSMIDDLENAME = 102;
	public static int MISSLASTNAME = 103;
	public static int MISSUSERNAME = 104;
	public static int USERNAMEEXISTS = 10401;
	public static int USERNAMENOTEXISTS = 10402;
	
	public static int MISSPHONENUMBER = 105;
	public static int PHONENUMBERNOTNUMBER = 10501;
	
	public static int MISSEMAIL = 106;
	public static int WRONGEMAILFORMAT = 10601;
	
	public static int MISSBIRTHDAY = 107;
	public static int WRONGBIRTHDAYFORMAT = 10701;
	public static int WRONGDOBMONTH = 10702;
	public static int WRONGDOBDAY = 10703;
	public static int WRONGDOBYEAR = 10704;
	
	public static int MISSPASSWORD = 108;
	public static int WRONGPASSWORD = 10801;
	public static int MISSCOMFIRMPASSWORD = 10802;
	public static int WRONGCOMFIRMPASSWORD = 10803;
	
	public static int NOENOUGHMONEY = 201;
	
	public static int NOSUCHCURRENCY = 301;
	public static int NOSUCHACCOUNT = 302;
}
