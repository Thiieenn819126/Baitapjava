package ex1;

public class Date {
	private int day=0;
	private int month=0;
	private int year=0;
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setDate(int day,int month,int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		
		
	}
	@Override
	public String toString() {
		 return String.format("%02d/%02d/%04d", day, month, year);
	}
	public static void main(String[] args) {
		 // Test constructor and toString()
	      Date d1 = new Date(1, 2, 2014);
	      System.out.println(d1);  // toString()

	      // Test Setters and Getters
	      d1.setMonth(12);
	      d1.setDay(9);
	      d1.setYear(2099);
	      System.out.println(d1);  // toString()
	      System.out.println("Month: " + d1.getMonth());
	      System.out.println("Day: " + d1.getDay());
	      System.out.println("Year: " + d1.getYear());

	      // Test setDate()
	      d1.setDate(3, 4, 2016);
	      System.out.println(d1);  // toString()
	}

}
