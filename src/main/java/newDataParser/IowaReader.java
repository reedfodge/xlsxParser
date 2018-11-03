package newDataParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IowaReader {
	public int serviceProviding = 0;
	public int government = 0;
	public int goodsProducing = 0;
	
	public int construction, education, fedGov, finActivity, info, 
		leisureHosp, localGov, manufacturing, miningLogging, other, 
	 	proBusinessServices, retailTrade, stateGov, transportUtil, 
		wholesaleTrade = 0; 


	public static void main(String args[]) throws IOException {
		File excelFile = new File("iowa.xlsx");
	    FileInputStream fis = new FileInputStream(excelFile);
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet sheet = workbook.getSheetAt(0);
	    
	    double employmentAverageService = getEmploymentAverage(service);
	    double employmentAverageGovernment = getEmploymentAverage(govern);
	    double employmentAverageGoods = getEmploymentAverage(goods);
	    
	    ArrayList<IowaDataType> totalArr = getCellInfo(sheet);
	    for(int i = 0; i < totalArr.size(); i++) {
	    	System.out.println(totalArr.get(i).toString());
	    }
	}
	
	public void getIowaStats(XSSFSheet sheet) {
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
	
	public static void printSheet(XSSFSheet sheet) {
		for(Row row : sheet) {
			for(Cell cell : row) {
				String cellText = cell.toString();
				System.out.print(cellText + " ");
			}
			System.out.println("");
		}
	}
	
	
	public static ArrayList<IowaDataType> getCellInfo(XSSFSheet sheet) {
		ArrayList<IowaDataType> list = new ArrayList<IowaDataType>();
		for(Row row : sheet) {
			IowaDataType test = new IowaDataType(" ", " ", 0, " ", " ", 0);
			for(Cell cell : row) {
				String cellText = cell.toString();
				boolean hasPeriod = false;
				for(int i = 1; i < cellText.length(); i++) {
					if(cellText.substring(i-1, i).equals("/")) {
						test.setMonthEnding(cellText);
					}
					else if(cellText.substring(i-1, i).equals(".")) {
						test.setEmployment(Double.parseDouble(cellText));
						hasPeriod = true;
					}
				}
				if(cellText.length() == 3 && hasPeriod == false) {
					test.setMonth(cellText);
				}
//				else if(cellText.length() == 4 && hasPeriod == false) {
//					test.setYear(Integer.getInteger(cellText));
//				}
				else if(cellText.equalsIgnoreCase("Service-Providing")) { test.setCategory(cellText); }
				else if(cellText.equalsIgnoreCase("Government")) { test.setCategory(cellText); }
				else if(cellText.equalsIgnoreCase("Goods-Producing")) { test.setCategory(cellText); }
				else if(cellText.equalsIgnoreCase("Construction")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Education and Health Services")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Federal Government")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Financial Activity")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Information")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Leisure and Hospitality")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Local Government")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Other Services")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Manufacturing")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Mining and Logging")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Professional Business and Services")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Retail Trade")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("State Government")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Transportation and Utilities")) { test.setSupersector(cellText); }
				else if(cellText.equalsIgnoreCase("Wholesale Trade")) { test.setSupersector(cellText); }
			}
			list.add(test);
		}
		return list;
	}
	
	public static ArrayList<IowaDataType> service = new ArrayList<IowaDataType>();
	public static ArrayList<IowaDataType> govern = new ArrayList<IowaDataType>();
	public static ArrayList<IowaDataType> goods = new ArrayList<IowaDataType>();
	
	public static void sort(XSSFSheet sheet) {
		ArrayList<IowaDataType> unsorted = new ArrayList<IowaDataType>();
		for(int i = 0; i < unsorted.size(); i++) {
			if(unsorted.get(i).getCategory().equals("Service-Providing")) {
				service.add(unsorted.get(i));
			}
			else if(unsorted.get(i).getCategory().equals("Government")) {
				govern.add(unsorted.get(i));
			}
			else if(unsorted.get(i).getCategory().equals("Goods-Production")) {
				goods.add(unsorted.get(i));
			}
		}
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
}
