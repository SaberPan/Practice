package com.saber.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.saber.bean.SudokuGrid;
import com.saber.util.ExcelUtils;

/**
 * @author Saber Pan
 * 
 */
public class InputService {

	public SudokuGrid inputByFile(String filePath) throws Exception {
		List<List<String>> list = ExcelUtils.getExcleContent(filePath);

		SudokuGrid sudokuGrid = new SudokuGrid();

		// y-index
		for (int i = 0; i < list.size(); i++) {
			List<String> array = list.get(i);
			// x-index
			for (int j = 0; j < array.size(); j++) {
				if (StringUtils.isNotBlank(array.get(j))) {
					sudokuGrid.setValue(Integer.parseInt(array.get(j)), j, i);
					sudokuGrid.setIsInput(j, i);
				}
			}
		}
		return sudokuGrid;
	}

}
