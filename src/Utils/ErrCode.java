package Utils;

public class ErrCode {

	public static int ERROR = 0;
	public static int OK = 1;
	
	//register
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
	public static int MISSDATE = 107;
	public static int WRONGDATEFORMAT = 10701;
	public static int WRONGDATEMONTH = 10702;
	public static int WRONGDATEDAY = 10703;
	public static int WRONGDATEYEAR = 10704;
	public static int MISSPASSWORD = 108;
	public static int WRONGPASSWORD = 10801;
	public static int MISSCOMFIRMPASSWORD = 10802;
	public static int WRONGCOMFIRMPASSWORD = 10803;
	
	//transaction
	public static int NOENOUGHMONEY = 201;
	public static int NOSUCHCURRENCY = 202;
	public static int NOSUCHACCOUNT = 203;
	
	//loan
	public static int LOANNAMEEMPTY = 301;
	public static int LOANNAMEEXIST = 30101;
	public static int LOANNAMENOTEXIST = 30102;
	public static int COLLATERALEMPTY = 302;
	public static int LOANNUMBEREMPTY = 303;
	public static int LOANCANNOTBEPAIED = 304;
}
