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
	
	public int getYear() {
		return this.year;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public String toString() {
		String string = "" + month + "/" + day + "/" + year;
		return string;
	}
}
