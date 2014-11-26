package com.saber.rule.impl;

import java.util.List;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

/**
 * @author Saber Pan
 * 
 */
public class RecessivenessExclusiveImproveOnY extends BaseAbstract implements IFindRule {

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValues) throws Exception {
		int falseCount = 0;
		int value = 0;
		for (Integer possibleValue : possibleValues) {
			boolean isOnlyValue = isOnlyValueOnY(sudokuGrid, x, y, possibleValue);
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

	private boolean isOnlyValueOnY(SudokuGrid sudokuGrid, int x, int y, int possibleValue) throws Exception {
		int gridX = x / 3;
		int gridY = y / 3;
		for (int xIndex = 0; xIndex < 3; xIndex++) {
			if (gridX == xIndex) {
				continue;
			}
			boolean isFind = true;
			for (int yIndex = 0; yIndex < 3; yIndex++) {
				int tempY = 3 * gridY + yIndex;
				if (y == tempY) {
					continue;
				}
				for (int subX = 0; subX < 3; subX++) {
					int tempX = 3 * xIndex + subX;
					List<Integer> otherGridPsossibleValues = sudokuGrid.getPossibleValue(tempX, tempY);
					if (otherGridPsossibleValues.contains(possibleValue)) {
						isFind = false;
						break;
					}
				}
			}
			if (isFind) {
				return true;
			}
		}

		return false;
	}
}
