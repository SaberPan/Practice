package com.saber.service;

import com.saber.bean.SudokuGrid;
import com.saber.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Saber Pan
 * 
 */
public class InputService {

	public SudokuGrid inputByFile(String filePath) throws Exception {
		List<List<String>> list = ExcelUtils.getExcelContent(filePath);

		SudokuGrid sudokuGrid = new SudokuGrid();

		// y-index
		for (int y = 0; y < list.size(); y++) {
			List<String> array = list.get(y);
			// x-index
			for (int x = 0; x < array.size(); x++) {
				if (StringUtils.isNotBlank(array.get(x))) {
					int value = Double.valueOf(array.get(x)).intValue();
					if (value != 0) {
						sudokuGrid.setValue(value, x, y);
						sudokuGrid.setIsInput(x, y);
					}
				}
			}
		}
		return sudokuGrid;
	}

}
