package com.saber.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.saber.bean.SudokuGrid;
import com.saber.constant.SudokuConstant;
import com.saber.rule.IFindRule;

/**
 * @author Saber Pan
 * 
 */
public class RemoveDuplicatedPossibleOnY extends BaseAbstract implements IFindRule {

	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		List<List<Integer>> otherList = new ArrayList<List<Integer>>();
		for (int gx = 0; gx < SudokuConstant.GRID_SIZE; gx++) {
			// Skip the point
			if (gx == x) {
				continue;
			}
			List<Integer> possibleList = sudokuGrid.getPossibleValue(gx, y);
			if (possibleList.size() == 2) {
				otherList.add(possibleList);
			}
		}

		return isFind(sudokuGrid, x, y, possibleValue, otherList);
	}

}
