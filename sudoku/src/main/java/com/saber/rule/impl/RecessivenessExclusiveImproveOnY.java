package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

import java.util.List;

/**
 *        |        |
 *        |        |
 *        |        |
 * -------------------------
 *        |        |
 *        |        |
 *        |        |
 * -------------------------
 *        |    B   |
 *  A     |    D   |
 *        |    C   |
 *
 * A has the some possible values, one of them is X. But in the grid[B,C], there is no X
 * on B and C horizontal line, so D must contain the X, then exclusive the X from A.
 * If A left one possible value, then set the only one value to the position.
 *
 * @author Saber Pan
 * 
 */
public class RecessivenessExclusiveImproveOnY extends BaseAbstract implements IFindRule {

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValues) throws Exception {
		int falseCount = 0;
		int value = 0;
		for (Integer possibleValue : possibleValues) {
			boolean isOnlyValue = super.isExclusiveValueOnY(sudokuGrid, x, y, possibleValue);
			if (!isOnlyValue) {
				value = possibleValue;
				falseCount++;
			}
			if (falseCount > 1) {
				return false;
			}
		}

		if (falseCount != 1) {
			return false;
		}

		LOG.info("[" + x + "," + y + "] - " + value);
		sudokuGrid.setValue(value, x, y);
		return true;
	}
}
