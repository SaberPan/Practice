package com.saber.service;

import com.saber.bean.SudokuGrid;
import com.saber.constant.SudokuConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Saber Pan
 * 
 */
public class OutputService {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFCellStyle colorStyle;
	private Map<Boolean, XSSFCellStyle> rightStyleMap = new HashMap<Boolean, XSSFCellStyle>();
	private Map<Boolean, XSSFCellStyle> buttomStyleMap = new HashMap<Boolean, XSSFCellStyle>();
	private Map<Boolean, XSSFCellStyle> rightButtomStyleMap = new HashMap<Boolean, XSSFCellStyle>();

	public OutputService() {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
		XSSFCellStyle rightStyle = workbook.createCellStyle();
		rightStyle.setBorderRight(BorderStyle.THIN);
		rightStyleMap.put(true, rightStyle);

		XSSFCellStyle rightStyleColor = workbook.createCellStyle();
		rightStyleColor.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		rightStyleColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		rightStyleColor.setBorderRight(BorderStyle.THIN);
		rightStyleMap.put(false, rightStyleColor);

		XSSFCellStyle buttomStyle = workbook.createCellStyle();
		buttomStyle.setBorderBottom(BorderStyle.THIN);
		buttomStyleMap.put(true, buttomStyle);

		XSSFCellStyle buttomStyleColor = workbook.createCellStyle();
		buttomStyleColor.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		buttomStyleColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		buttomStyleColor.setBorderBottom(BorderStyle.THIN);
		buttomStyleMap.put(false, buttomStyleColor);

		XSSFCellStyle rightButtomStyle = workbook.createCellStyle();
		rightButtomStyle.setBorderBottom(BorderStyle.THIN);
		rightButtomStyle.setBorderRight(BorderStyle.THIN);
		rightButtomStyleMap.put(true, rightButtomStyle);

		XSSFCellStyle rightButtomStyleColor = workbook.createCellStyle();
		rightButtomStyleColor.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		rightButtomStyleColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		rightButtomStyleColor.setBorderBottom(BorderStyle.THIN);
		rightButtomStyleColor.setBorderRight(BorderStyle.THIN);
		rightButtomStyleMap.put(false, rightButtomStyleColor);

		colorStyle = workbook.createCellStyle();
		colorStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		colorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		for (int columnIndex = 0; columnIndex < SudokuConstant.GRID_SIZE; columnIndex++) {
			sheet.setColumnWidth(columnIndex, 650);
		}
	}

	public void output2File(String filePath, SudokuGrid sukGrid) throws Exception {
		for (int y = 0; y < SudokuConstant.GRID_SIZE; y++) {
			XSSFRow row = sheet.createRow(y);
			for (int x = 0; x < SudokuConstant.GRID_SIZE; x++) {
				XSSFCell cell = row.createCell(x);

				setCellStyle(sukGrid.isInput(x, y), y, x, cell);
				Integer value = sukGrid.getValue(x, y);
				if (value == null) {
					// cell.setCellValue("");
					cell.setCellValue(ArrayUtils.toString(sukGrid.getPossibleValue(x, y)));
				} else {
					cell.setCellValue(value);
				}
			}
		}
		workbook.write(new FileOutputStream(filePath));
	}

	public void output2FileForOriginal(String filePath, SudokuGrid sukGrid) throws Exception {
		for (int y = 0; y < SudokuConstant.GRID_SIZE; y++) {
			XSSFRow row = sheet.createRow(y);
			for (int x = 0; x < SudokuConstant.GRID_SIZE; x++) {
				XSSFCell cell = row.createCell(x);

				setCellStyle(true, y, x, cell);
				Integer value = sukGrid.getValue(x, y);
				if (value == null) {
					cell.setCellValue("");
				} else {
					cell.setCellValue(value);
				}
			}
		}
		workbook.write(new FileOutputStream(filePath));
	}

	private void setCellStyle(boolean isInput, int y, int x, XSSFCell cell) {
		if ((x + 1) % 3 == 0) {
			if ((y + 1) % 3 == 0) {
				cell.setCellStyle(rightButtomStyleMap.get(isInput));
			} else {
				cell.setCellStyle(rightStyleMap.get(isInput));
			}
		} else {
			if ((y + 1) % 3 == 0) {
				cell.setCellStyle(buttomStyleMap.get(isInput));
			} else {
				if (!isInput) {
					cell.setCellStyle(colorStyle);
				}
			}
		}
	}
}
