package model;

public class Date {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	public Date(){
		
	}
	
	public Date(int month, int day, int year) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public Date(int month, int day, int year, int hour, int minute, int second) {
		this(month, day, year);
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getDay() {
		return this.day;
	}
	
	
	public String toDayString() {
		String string = "" + month + "/" + day + "/" + year;
		return string;
	}
	
	public String toTimeString() {
		String string = "" + month + "/" + day + "/" + year +" " + hour + ":" + minute + ":" +second;
		return string;
	}
	
}
