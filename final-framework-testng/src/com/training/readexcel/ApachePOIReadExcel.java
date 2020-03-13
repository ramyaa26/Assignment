package com.training.readexcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIReadExcel {
	
	
	
	public String ReadValuesFromExcel(String filename,String sheetname,int row,int col) throws IOException{
		
		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetname);
		XSSFCell cell = sheet.getRow(row).getCell(col);
	
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String rowvalue = sheet.getRow(row).getCell(col).getStringCellValue();
		
		workbook.close();
		
		return rowvalue;
	}

}
