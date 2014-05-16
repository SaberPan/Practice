package com.saber;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.saber.bean.SudokuGrid;
import com.saber.service.Calculator;
import com.saber.service.InputService;
import com.saber.service.OutputService;
import com.saber.util.PathUtils;

/**
 * @author Saber Pan
 * 
 */
public class Main {

	private static Logger LOG = Logger.getLogger(Main.class);
	private static Scanner scanner;

	public static void main(String[] args) throws Exception {
		System.setProperty("WORKDIR", PathUtils.getProjectRoot());
		System.out.println("Please input the fileName: ");
		scanner = new Scanner(System.in);
		String fileName = scanner.next();
		execute(fileName);
	}

	public static void execute(String fileName) throws Exception {
		InputService service = new InputService();
		String extension = ".xlsx";
		String filePath = PathUtils.getPathInProject("data/" + fileName + extension);
		LOG.info(filePath);
		SudokuGrid sudokuGrid = service.inputByFile(filePath);

		boolean flag = false;
		try {
			Calculator calculator = new Calculator();
			flag = calculator.find(sudokuGrid);
			LOG.info("IsFind: " + flag);
		} finally {
			String outputFile = PathUtils.getPathInProject("/data/" + fileName + "_" + flag + extension);
			new OutputService().output2File(outputFile, sudokuGrid);
		}
	}
}
