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
public class RemoveDuplicatedPossibleOnX extends BaseAbstract implements IFindRule {

	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		List<List<Integer>> otherList = new ArrayList<List<Integer>>();
		for (int gy = 0; gy < SudokuConstant.GRID_SIZE; gy++) {
			// Skip the point
			if (gy == y) {
				continue;
			}
			List<Integer> possibleList = sudokuGrid.getPossibleValue(x, gy);
			if (possibleList.size() == 2) {
				otherList.add(possibleList);
			}
		}

		return isFind(sudokuGrid, x, y, possibleValue, otherList);
	}

}
