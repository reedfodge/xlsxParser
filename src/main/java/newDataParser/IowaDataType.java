package newDataParser;

public class IowaDataType {
	private String monthEnding;
	private String month;
	private String year;
	private String category;
	private String supersector;
	private double employment;
	

	public IowaDataType(String monthEnding, String month, String year, String category, String supersector, double employment) {
		this.monthEnding = monthEnding;
		this.month = month;
		this.year = year;
		this.category = category;
		this.supersector = supersector;
		this.employment = employment;
	}
	
	public IowaDataType() {
		this.monthEnding = "";
		this.month = "";
		this.year = "";
		this.category = "";
		this.supersector = "";
		this.employment = 0;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String toString() {
		String s = this.getMonthEnding() + " " + this.getMonth() + " " + this.getYear() + " " + this.getCategory() + " " + this.getSupersector() + " " + this.getEmployment();
		return s;
	}
}
