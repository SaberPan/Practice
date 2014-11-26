package com.saber.rule.impl;

import java.util.List;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

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
			boolean isOnlyValue = isOnlyValueOnX(sudokuGrid, x, y, possibleValue);
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

	private boolean isOnlyValueOnX(SudokuGrid sudokuGrid, int x, int y, int possibleValue) throws Exception {
		int gridX = x / 3;
		int gridY = y / 3;
		for (int yIndex = 0; yIndex < 3; yIndex++) {
			if (gridY == yIndex) {
				continue;
			}
			boolean isFind = true;
			for (int xIndex = 0; xIndex < 3; xIndex++) {
				int tempX = 3 * gridX + xIndex;
				if (x == tempX) {
					continue;
				}
				for (int subY = 0; subY < 3; subY++) {
					int tempY = 3 * yIndex + subY;
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
