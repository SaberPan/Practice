package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.constant.SudokuConstant;
import com.saber.rule.IFindRule;

import java.util.List;

/**
 *    A   |         |
 *    B   |         |    C
 *        |         |
 * -------------------------
 *        |         |
 *        |         |
 *        |         |
 * -------------------------
 *        |         |
 *        |         |
 *        |         |
 *
 * A has some possible values, if one of them is X, and only B has the X possible value on the vertical line.
 * But through the horizontal line at C position, exclusive the X.
 * So make sure A is X.
 *
 * @author: Saber Pan
 */
public class IndirectRecessivenessExclusiveOnX extends BaseAbstract implements IFindRule {

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValues) throws Exception {
		for (Integer possibleValue : possibleValues) {
			if (isOnlyValueOnX(possibleValue, sudokuGrid, x, y)) {
				LOG.info("[" + x + "," + y + "] - " + possibleValue);
				sudokuGrid.setValue(possibleValue, x, y);
				return true;
			}
		}

		return false;
	}

	private boolean isOnlyValueOnX(Integer possibleValue, SudokuGrid sudokuGrid, int x, int y) throws Exception {
		int otherContainCount = 0;
		int containXIndex = 0;
		for (int yIndex = 0; yIndex < SudokuConstant.GRID_SIZE; yIndex++) {
			if (yIndex != y) {
				List<Integer> otherPossibleValueList = sudokuGrid.getPossibleValue(x, yIndex);
				if (otherPossibleValueList.contains(possibleValue)) {
					containXIndex = yIndex;
					otherContainCount++;

					if (otherContainCount > 1) {
						return false;
					}
				}
			}
		}
		return super.isExclusiveValueOnY(sudokuGrid, x, containXIndex, possibleValue);
	}
}
