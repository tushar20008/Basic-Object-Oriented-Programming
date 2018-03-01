import java.io.*;
import java.util.Scanner;

public class Day implements Cloneable{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";
	
	
	public Day clone()
	{
		Day copy=null;
		try{
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return copy;
	}
	//Constructor
	 public Day(String sDay)
	 {
		 set(sDay);
	 }
	 
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	
	 public void set(String sDay) //Set year,month,day based on a string like 01-Mar-2014
	 {
		 String[] sDayParts = sDay.split("-");
		 this.day =Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
		 this.year =Integer.parseInt(sDayParts[2]);
		 this.month = MonthNames.indexOf(sDayParts[1])/3+1;
	}
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}
	
	public String toString()
	{
		return day+"-"+ MonthNames.substring((month-1)*3,(month)*3) + "-"+ year; 
	}
	public String dateFormat()
	{
		String day_0="-", month_0="-";
		if(day < 10)
			day_0="-0";
		if(month < 10)
			month_0="-0";
		return year+month_0+ month +day_0+ day;
	}
	public static int countLeapYears(Day d)
	{
	    int years = d.year;
	 
	    // Check if the current year needs to be considered
	    // for the count of leap years or not
	    if (d.month <= 2)
	        years--;
	 
	    // An year is a leap year if it is a multiple of 4,
	    // multiple of 400 and not a multiple of 100.
	    return years / 4 - years / 100 + years / 400;
	}
	 
	public static int calVacationLength(Day start, Day end )
	{
		//int length=end.day-start.day+1;	// Not considering Months and Year
		//return length;
		int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int d1 = start.year*365 + start.day;
		 
	    // Adding days for months in given date
	    for (int i=0; i<start.month - 1; i++)
	        d1 += monthDays[i];
	 
	    // Since every leap year is of 366 days,
	    // Add a day for every leap year
	    d1 += countLeapYears(start);
	 
	    // SIMILARLY, COUNT TOTAL NUMBER OF DAYS BEFORE 'd2'
	 
	    int d2 = end.year*365 + end.day;
	    for (int i=0; i<end.month - 1; i++)
	        d2 += monthDays[i];
	    d2 += countLeapYears(end);
	 
	    // return difference between two counts
	    return (d2 - d1)+1;
	}
}