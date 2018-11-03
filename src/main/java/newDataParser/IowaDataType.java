package newDataParser;

public class IowaDataType {
	public String monthEnding;
	public String month;
	public int year;
	public String category;
	public String supersector;
	public double employment;
	

	public IowaDataType(String monthEnding, String month, int year, String category, String supersector, double employment) {
		this.monthEnding = monthEnding;
		this.month = month;
		this.year = year;
		this.category = category;
		this.supersector = supersector;
		this.employment = employment;
	}
	
	public String getMonthEnding() {
		return monthEnding;
	}
	public void setMonthEnding(String monthEnding) {
		this.monthEnding = monthEnding;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSupersector() {
		return supersector;
	}
	public void setSupersector(String supersector) {
		this.supersector = supersector;
	}
	public double getEmployment() {
		return employment;
	}
	public void setEmployment(double employment) {
		this.employment = employment;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString(IowaDataType i) {
		return i.getMonthEnding() + " " + i.getMonth() + " " + i.getCategory() + " " + i.getSupersector() + i.getEmployment() + i.getYear();
	}
}
