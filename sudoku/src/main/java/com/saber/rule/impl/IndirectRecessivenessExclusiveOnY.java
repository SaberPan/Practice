package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.constant.SudokuConstant;
import com.saber.rule.IFindRule;

import java.util.List;

/**
 *        |         |
 *     C  |         |
 *        |         |
 * -------------------------
 *        |         |
 *        |         |
 *        |         |
 * -------------------------
 *        |         |
 *  A  B  |         |
 *        |         |
 *
 * A has some possible values, if one of them is X, and only B has the X possible value on the horizontal line.
 * But through the vertical line at C position, exclusive the X.
 * So make sure A is X.
 *
 * @author: Saber Pan
 */
public class IndirectRecessivenessExclusiveOnY extends BaseAbstract implements IFindRule {

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValues) throws Exception {
		for (Integer possibleValue : possibleValues) {
			if (isOnlyValueOnY(possibleValue, sudokuGrid, x, y)) {
				LOG.info("[" + x + "," + y + "] - " + possibleValue);
				sudokuGrid.setValue(possibleValue, x, y);
				return true;
			}
		}

		return false;
	}

	private boolean isOnlyValueOnY(Integer possibleValue, SudokuGrid sudokuGrid, int x, int y) throws Exception {
		int otherContainCount = 0;
		int containXIndex = 0;
		for (int xIndex = 0; xIndex < SudokuConstant.GRID_SIZE; xIndex++) {
			if (xIndex != x) {
				List<Integer> otherPossibleValueList = sudokuGrid.getPossibleValue(xIndex, y);
				if (otherPossibleValueList.contains(possibleValue)) {
					containXIndex = xIndex;
					otherContainCount++;

					if (otherContainCount > 1) {
						return false;
					}
				}
			}
		}
		return super.isExclusiveValueOnX(sudokuGrid, containXIndex, y, possibleValue);
	}

}
