package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

import java.util.List;

/**
 * @author Saber Pan
 * 
 */
public class RecessivenessExclusiveImproveOnX extends BaseAbstract implements IFindRule {

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValues) throws Exception {
		int falseCount = 0;
		int value = 0;
		for (Integer possibleValue : possibleValues) {
			boolean isOnlyValue = super.isExclusiveValueOnX(sudokuGrid, x, y, possibleValue);
			if (!isOnlyValue) {
				value = possibleValue;
				falseCount++;
			}
			if (falseCount > 1) {
				return false;
			}
		}

		LOG.info("[" + x + "," + y + "] - " + value);
		sudokuGrid.setValue(value, x, y);
		return true;
	}

}
