/**
 * 
 */
package com.saber.service;

import org.apache.log4j.Logger;

import com.saber.bean.SudokuGrid;
import com.saber.constant.SudokuConstant;
import com.saber.rule.FindRuleOperator;

/**
 * @author Saber Pan
 * 
 */
public class Calculator {

	private Logger LOG = Logger.getLogger(Calculator.class);

	public boolean find(SudokuGrid sudokuGrid) throws Exception {
		int count = 0;
		while (!sudokuGrid.isOver()) {
			count++;
			boolean isFind = findLeastPossibleValue(sudokuGrid);
			if (!isFind) {
				break;
			}
		}
		LOG.info("count=" + count);
		return sudokuGrid.isRight();
	}

	private boolean findLeastPossibleValue(SudokuGrid sudokuGrid) throws Exception {
		boolean isFind = false;
		// y-index
		for (int i = 0; i < SudokuConstant.GRID_SIZE; i++) {
			// x-index
			for (int j = 0; j < SudokuConstant.GRID_SIZE; j++) {
				try {
					if (FindRuleOperator.getInstance().find(sudokuGrid, j, i)) {
						isFind = true;
					}
				} catch (Exception e) {
					throw new Exception("[" + j + "," + i + "]: ", e);
				}
			}
		}
		return isFind;
	}
}
