package newDataParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IowaReader {
	public static int serviceProviding = 0;
	public static int government = 0;
	public static int goodsProducing = 0;
	
	public static int construction, education, fedGov, finActivity, info, 
		leisureHosp, localGov, manufacturing, miningLogging, other, 
	 	proBusinessServices, retailTrade, stateGov, transportUtil, 
		wholesaleTrade = 0; 


	public static void main(String args[]) throws IOException {
		File excelFile = new File("iowa.xlsx");
	    FileInputStream fis = new FileInputStream(excelFile);
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    
	   getIowaStats(sheet);
	   fullList = getData(sheet);
	   sort(sheet);
	   sortByYear(govern);
	   //sortByMonth(govern);
	   for(int i = 0; i < govern.size(); i++) {
		   System.out.println(govern.get(i).toString());
	   }
	}
	
	public static String[] months = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
	
	public static void printEverything() {
		for(int i = 0; i < fullList.size(); i++) {
			System.out.println(fullList.get(i).toString());
		}
	}
	
	public static void printList(ArrayList<IowaDataType> arr) {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).toString());
		}
	}
	
	public static void getIowaStats(XSSFSheet sheet) {
		for(Row row : sheet) {
			for(Cell cell : row) {
				String cellText = cell.toString();
				if(cellText.equalsIgnoreCase("Service-Providing")) { serviceProviding++; }
				else if(cellText.equalsIgnoreCase("Government")) { government++; }
				else if(cellText.equalsIgnoreCase("Goods-Producing")) { goodsProducing++; }
				else if(cellText.equalsIgnoreCase("Construction")) { construction++; }
				else if(cellText.equalsIgnoreCase("Education and Health Services")) { education++; }
				else if(cellText.equalsIgnoreCase("Federal Government")) { fedGov++; }
				else if(cellText.equalsIgnoreCase("Financial Activity")) { finActivity++; }
				else if(cellText.equalsIgnoreCase("Information")) { info++; }
				else if(cellText.equalsIgnoreCase("Leisure and Hospitality")) { leisureHosp++; }
				else if(cellText.equalsIgnoreCase("Local Government")) { localGov++; }
				else if(cellText.equalsIgnoreCase("Other Services")) { other++; }
				else if(cellText.equalsIgnoreCase("Manufacturing")) { manufacturing++; }
				else if(cellText.equalsIgnoreCase("Mining and Logging")) { miningLogging++; }
				else if(cellText.equalsIgnoreCase("Professional Business and Services")) { proBusinessServices++; }
				else if(cellText.equalsIgnoreCase("Retail Trade")) { retailTrade++; }
				else if(cellText.equalsIgnoreCase("State Government")) { stateGov++; }
				else if(cellText.equalsIgnoreCase("Transportation and Utilities")) { transportUtil++; }
				else if(cellText.equalsIgnoreCase("Wholesale Trade")) { wholesaleTrade++; }
			}
		}
	}
	
	
	public static ArrayList<IowaDataType> fullList;
	
	public static ArrayList<IowaDataType> getData(XSSFSheet sheet) {
		ArrayList<IowaDataType> arr = new ArrayList<IowaDataType>();
		for(Row row : sheet) {
			IowaDataType test = new IowaDataType();
			for(Cell cell : row) {
				String cellText = cell.toString();
				boolean hasPeriod = false;
				boolean hasSlash = false;
				for(int i = 1; i < cellText.length(); i++) {
					if(cellText.substring(i-1, i).equalsIgnoreCase(".")) {
						hasPeriod = true;
					}
					else if(cellText.substring(i-1, i).equalsIgnoreCase(("-"))) {
						hasSlash = true;
					}
				}
				if(hasPeriod && cellText.length() <= 4) {
					test.setEmployment(Double.valueOf(cellText));
				}
				else if(hasSlash && cellText.length() < 12) {
					test.setMonthEnding(cellText);
				}
				else if(cellText.length() == 3 && hasPeriod == false) {
					test.setMonth(cellText);
				}
				else if(cellText.length() == 6 && hasPeriod == true) {
					test.setYear(cellText);
				}
				else if(cellText.equalsIgnoreCase("Service-Providing") || cellText.equalsIgnoreCase("Government") || cellText.equalsIgnoreCase("Goods-Producing")) {
					test.setCategory(cellText);
				}
				else {
					test.setSupersector(cellText);
				}
			}
			arr.add(test);
		}
		return arr;
	}

	
	public static ArrayList<IowaDataType> service = new ArrayList<IowaDataType>();
	public static ArrayList<IowaDataType> govern = new ArrayList<IowaDataType>();
	public static ArrayList<IowaDataType> goods = new ArrayList<IowaDataType>();
	
	public static void sort(XSSFSheet sheet) {
		ArrayList<IowaDataType> unsorted = getData(sheet);
		for(int i = 0; i < unsorted.size(); i++) {
			if(unsorted.get(i).getCategory().equals("Service-Providing")) {
				service.add(unsorted.get(i));
			}
			else if(unsorted.get(i).getCategory().equals("Government")) {
				govern.add(unsorted.get(i));
			}
			else if(unsorted.get(i).getCategory().equals("Goods-Producing")) {
				goods.add(unsorted.get(i));
			}
		}
	}
	
	public static ArrayList<String> getDifferences(XSSFSheet sheet) {
		ArrayList<IowaDataType> arr = service;
		ArrayList<String> diff = new ArrayList<String>();
		for(int i = 1; i < arr.size(); i++) {
			double d = arr.get(i).getEmployment() - arr.get(i-1).getEmployment();
			d = Math.abs(d);
			String s = Double.toString(d);
			diff.add(s);
		}
		return diff;
	}
	
	public static void toDate(IowaDataType i) {
		String s = i.getMonthEnding();
		System.out.println(s);
		int day = Integer.getInteger(s.substring(0, 2));
		String month = s.substring(3, 5);
		int mon = 0;
		String[] months = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
		for(int j = 0; j < months.length; j++) {
			if(month.equals(months[j])) {
				mon = j+1;
			}
		}
		int year = Integer.getInteger(s.substring(7, 10));
	//	System.out.println(day);
	//	System.out.println(month);
	//	System.out.println(year);
	}
	
	public static double getEmploymentAverage(ArrayList<IowaDataType> list) {
		double average = 0;
		for(int i = 0; i < list.size(); i++) {
			average += list.get(i).getEmployment();
		}
		average /= list.size();
		return average;
	}
	
	public static void printArray() {
		System.out.println(service.size());
		for(int i = 0; i < service.size(); i++) {
			System.out.println(service.get(i).toString());
		}
 	}
	
	public static void sortByYear(ArrayList<IowaDataType> arr) {
		double low = 1000000;
		double high = 0;
		for(int i = 0; i < arr.size(); i++) {
			if(Double.valueOf(arr.get(i).getYear()) < low) {
				low = Double.valueOf(arr.get(i).getYear());
			}
			else if(Double.valueOf(arr.get(i).getYear()) > high) {
				high = Double.valueOf(arr.get(i).getYear());
			}
		}
		for(int i = 0; i < arr.size(); i++) {
			for(int j = 0; j < arr.size(); j++) {
				IowaDataType temp = arr.get(i);
				if(Double.valueOf(arr.get(i).getYear()) < Double.valueOf(arr.get(j).getYear())) {
					arr.set(i, arr.get(j));
					arr.set(j, temp);
				}
			}
		}
	}
	
	public static void sortByMonth(ArrayList<IowaDataType> arr) {
		ArrayList<IDTIndex> arrIndex = new ArrayList<IDTIndex>();
		for(int i = 0; i < arr.size(); i++) {
			for(int j = 0; j < months.length; j++) {
				if(arr.get(i).getMonth().equalsIgnoreCase(months[j])) {
					int ind = j + 1;
					arrIndex.add(new IDTIndex(arr.get(i), ind));
					//System.out.println(arr.get(i).getMonth() + " index is " + ind);
				}
			}
		}
		
		for(int i = 0; i < arr.size(); i++) {
			for(int j = 0; j < arr.size(); j++) {
				IowaDataType temp = arr.get(i);
				if(arrIndex.get(i).getIndex() < arrIndex.get(j).getIndex() && arr.get(i).getYear().equals(arr.get(j).getYear())) {
					arr.set(i, arr.get(j));
					arr.set(j, temp);
					
				}
			}
		}
	}
}
