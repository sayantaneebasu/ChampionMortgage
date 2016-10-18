package com.automation.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.WebDriver;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;


public class ExcelUtil {

	public static ListMultimap<String, String> getSearchData1(String SheetName,String FilePath){
		//LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		ListMultimap<String, String> myMultimap = ArrayListMultimap.create();

		try {
			//System.out.println("*******************inside getSearchData************");
			FileInputStream fs = new FileInputStream(FilePath);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(SheetName);
			HSSFRow row = null ; 
			String value=null;
			int maxrow = sheet.getLastRowNum();
			//System.out.println("**********ROWNUM"+maxrow);
			ArrayList<String> headers = new ArrayList(Arrays.asList("payer ID","payer Name","Tax ID","National Payer ID"));

			//System.out.println("*******************inside getSearchData************"+headers.size());

			for(int i=1;i<maxrow+1;i++){
				for(int h=0;h<headers.size();h++){
					row = sheet.getRow(i);
					//System.out.println("*********getLastCellNum***********"+row.getLastCellNum()); 
					Cell cell = row.getCell(h,row.CREATE_NULL_AS_BLANK);
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						value =String.valueOf(cell.getNumericCellValue());
					} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						value =cell.getRichStringCellValue().toString();
					}  else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						value ="";
					}
					myMultimap.put(headers.get(h), value);	

					//System.out.println("*************Key"+ headers.get(h)+"**********Value**************"+value);

				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*for(Entry<String,String> myEntry:myMultimap.entries())
		{
			System.out.println("***********MULTIMAP KEY/VALUE*************"+myEntry.getKey()+myEntry.getValue());

		}*/
		return myMultimap;
	}

	public static LinkedHashMap getSearchData(String SheetName,String FilePath){
		LinkedHashMap<String,ArrayList<String>> map = new LinkedHashMap<String,ArrayList<String>>();
		ArrayList<String> colvalues = new ArrayList<String>();

		try {
			//System.out.println("*******************inside getSearchData************");
			FileInputStream fs = new FileInputStream(FilePath);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(SheetName); 
			HSSFRow row = null ; 
			String value=null;
			int maxrow = sheet.getLastRowNum();
			//System.out.println("**********ROWNUM"+maxrow);
			ArrayList<String> headers = new ArrayList(Arrays.asList("payer ID","payer Name","Tax ID","National Payer ID"));
			//System.out.println("*******************inside getSearchData************"+headers.size());
			for(int h=0;h<headers.size();h++){
				ArrayList<String> colValues=new ArrayList<String>();
				for(int i=1;i<maxrow+1;i++){

					row = sheet.getRow(i);
					//System.out.println("*********NEWLastCellNum***********"+row.getLastCellNum()); 
					Cell cell = row.getCell(h,row.CREATE_NULL_AS_BLANK);
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						value =String.valueOf(cell.getNumericCellValue());
					} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						value =cell.getRichStringCellValue().toString();
					}  else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						value ="";
					}
					colValues.add(value);


				}



				/*	if(map.containsKey(headers.get(h))){
		 			//colvalues.clear();
		 			colvalues.addAll(map.get(headers.get(h)));
					}*/

				map.put(headers.get(h), colValues);


				//System.out.println("*************NEW KEY****"+ headers.get(h)+"**********NEW Value**************"+value);
				// map.put(headers.get(h), colvalues);
				/*for(Entry<String, ArrayList<String>> entry:map.entrySet()){
					System.out.println("***************entry KEY*******"+entry.getKey());
					System.out.println("***************entry VALUES***********"+entry.getValue());
				}*/


			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static Object[][] readExcelValues(String SheetName,String FilePath){
		try {
			//System.out.println("*******************inside getSearchData************");
			FileInputStream fs = new FileInputStream(FilePath);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(SheetName); 
			HSSFRow row = null ; 
			String value=null;
			int maxrow = sheet.getLastRowNum();
			int maxcol =sheet.getRow(0).getLastCellNum();
			Object data[] = new Object[maxrow][maxcol];
			for(int i=2;i<maxrow;i++){
				for(int j=0;j<maxrow;j++){
					//data[i-2][j]=getCellData("sheet",i,j);
				}
				
			}
			

	}catch(Exception e){
		e.printStackTrace();
	}
		return null;
	}

	private static Object getCellData(String string, int i, int j) { 
		
		// TODO Auto-generated method stub
		return null;
	}

	public static LinkedHashMap<String, String> getExcelInputData(String SheetName,String FilePath,String TCName) {
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		try {
			FileInputStream fs = new FileInputStream(FilePath);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet(SheetName);
			int colnum=sheet.getRow(0).getLastCellNum();
			int intRow = findRowNumber(sheet, TCName);
			//System.out.println("************intRow***************"+intRow);

			for (int i = 0; i < colnum; i++) {
				parameters.put(sheet.getRow(0).getCell(i+1,Row.CREATE_NULL_AS_BLANK).getStringCellValue(), sheet.getRow(intRow).getCell(i+1,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
			}	
		/*	for(Entry<String,String> entry:parameters.entrySet()){
				System.out.println("***********Parameter Key************"+entry.getKey());
				System.out.println("*************Test Case********"+entry.getValue());
			}*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return parameters;
	}

	public LinkedHashMap<String, String> getInputData(String TCName) {
		LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
		try {
			FileInputStream fs = new FileInputStream("resources/excel/Input/Input.xls");
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet("TestData");

			int intRow = findRowNumber(sheet, TCName);

			for (int i = 0; i <= sheet.getRow(intRow-1).getLastCellNum()-1; i++) {
				parameters.put(sheet.getRow(intRow-1).getCell(i,Row.CREATE_NULL_AS_BLANK).getStringCellValue(), sheet.getRow(intRow).getCell(i,Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				System.out.println("***********Parameter Key************"+parameters.entrySet());
				System.out.println("*************Test Case********"+parameters.get("TestCase"));
				System.out.println("*************Test Case********"+parameters.get("Password"));
				System.out.println("*************Test Case********"+parameters.get("test_method"));
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return parameters;
	}
	public String setupOutput() {
		String fileName = new SimpleDateFormat("'resources/excel/Output/Output_'yyyyMMdd_hhmmss'.xls'").format(new Date());
		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			HSSFWorkbook workBook = new HSSFWorkbook();

			workBook.write(outputStream);
			outputStream.writeTo(fout);
			outputStream.close();
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public void writeOutput(String fileName, LinkedHashMap<String, String> parameters, LinkedList<LinkedHashMap<String, String>> results) {

		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.createSheet(parameters.get("tc_name"));			    

			int rownum = 0;
			Row row = sheet.createRow(rownum++);

			//Create the header
			int cellnum = 0;
			row.createCell(cellnum++).setCellValue("TestCase Name");
			row.createCell(cellnum++).setCellValue("Test Class and Method");
			row.createCell(cellnum++).setCellValue("Step Description");
			row.createCell(cellnum++).setCellValue("Status");
			row.createCell(cellnum++).setCellValue("Expected Result");
			row.createCell(cellnum++).setCellValue("Actual Result");
			row.createCell(cellnum++).setCellValue("Browser");
			row.createCell(cellnum++).setCellValue("Exception (if any)");

			//Fill the rows
			for(LinkedHashMap<String, String> result : results) {
				row = sheet.createRow(rownum++);
				cellnum = 0;
				row.createCell(cellnum++).setCellValue(parameters.get("tc_name"));
				row.createCell(cellnum++).setCellValue(parameters.get("test_class") + " - " + parameters.get("test_method"));
				row.createCell(cellnum++).setCellValue(result.get("Description"));
				row.createCell(cellnum++).setCellValue(result.get("Status"));
				row.createCell(cellnum++).setCellValue(result.get("ExpectedResult"));
				row.createCell(cellnum++).setCellValue(result.get("ActualResult"));
				row.createCell(cellnum++).setCellValue(result.get("Browser"));
				row.createCell(cellnum++).setCellValue(result.get("Exception"));
			}


			//Alternate way to iterate through the map with map entry
			//			int rownum = 0;
			//		    for(LinkedHashMap<String, String> result : results) {
			//				if (rownum ==0) {
			//					Row row = sheet.createRow(rownum++);
			//					int cellnum = 0;
			//					for (Map.Entry<String, String> item : result.entrySet()) {
			//						Cell cell = row.createCell(cellnum++);
			//						cell.setCellValue(item.getKey());
			//					}					
			//				}
			//				Row row = sheet.createRow(rownum++);
			//				int cellnum = 0;
			//				for (Map.Entry<String, String> item : result.entrySet()) {
			//					Cell cell = row.createCell(cellnum++);
			//					cell.setCellValue(item.getValue());
			//				}
			//		    }

			file.close();

			FileOutputStream outFile =new FileOutputStream(new File(fileName));
			workbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static int findRowNumber(HSSFSheet sheet, String cellData) {
		for (Row row : sheet) {
			Cell cell = row.getCell(0,Row.CREATE_NULL_AS_BLANK);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().trim().equals(cellData)) {
					return row.getRowNum();
				}
			}
		}
		return 0;
	}
	
	public static ArrayList<String> findRowNumber(String FilePath,String sheetName, String cellData) { 
		ArrayList<String> testCaseNames = new ArrayList();
		try{
		FileInputStream fs = new FileInputStream(FilePath);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(sheetName);
		
		String testCaseName ="";
		for (Row row : sheet) {
			Cell cell = row.getCell(0,Row.CREATE_NULL_AS_BLANK);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().trim().equals(cellData)) {
					//System.out.println("***************LASTROWNUM*******************"+sheet.getLastRowNum());
					for(int i=1;i<=sheet.getLastRowNum();i++){
						testCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
						testCaseNames.add(testCaseName);
						//System.out.println("TESTCASENAMES"+testCaseName);
					}
					
				 
				}
			}
		}
		
	
	}catch(Exception e){
		e.printStackTrace();
	}
		return testCaseNames;
}
	
	public static void writeBooleanResult(String fileName,String sheetName,int count,Boolean result){
		FileInputStream file;
		try {
			file = new FileInputStream(new File(fileName));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheet(sheetName);
			HSSFRow row = null;
			HSSFCell cell =null;
			int rownum = 0,colnum = 0;
			row = sheet.getRow(count);
			
			colnum =row.getLastCellNum();
			System.out.println("******************COLNUM*************"+colnum);
			row.createCell(colnum-1).setCellValue(result);
			file.close();
			  FileOutputStream output_file =new FileOutputStream(new File(fileName));  //Open FileOutputStream to write updates
              
			  workbook.write(output_file); //write changes
                
              output_file.close();
			

			//Create the header
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

	public static void main(String args[]){
		System.out.println("*************Inside Main*****************");
		findRowNumber("resources/excel/Input/Input.xls","Sheet1","TestCase");
	}


}
