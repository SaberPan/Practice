package com.saber.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Saber Pan
 * 
 */
public class ExcelUtils {

	public static List<List<String>> getExcleContent(String filePath) throws IOException {
		InputStream input = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheetAt(0);
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row != null) {
				List<String> rowList = new ArrayList<String>();
				for (short j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					rowList.add(row.getCell(j).getRawValue());
				}
				list.add(rowList);
			}
		}
		return list;
	}

	public static void write2Excel(List<List<String>> list, String path) throws IOException {
		if (list == null) {
			return;
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow(i);
			List<String> rowList = list.get(i);
			for (int j = 0; j < rowList.size(); j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellValue(rowList.get(j));
			}
		}

		OutputStream out = new FileOutputStream(path);
		workbook.write(out);
	}
}
