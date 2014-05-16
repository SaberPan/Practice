package com.saber.rule.impl;

import java.util.ArrayList;
import java.util.List;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

/**
 * @author Saber Pan
 * 
 */
public class RemoveDuplicatedPossible extends BaseAbstract implements IFindRule {

	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		List<List<Integer>> otherList = new ArrayList<List<Integer>>();
		for (int gridY = 0; gridY < 3; gridY++) {
			int gy = 3 * (y / 3) + gridY;
			for (int gridX = 0; gridX < 3; gridX++) {
				int gx = 3 * (x / 3) + gridX;
				if (gx != x || gy != y) {
					List<Integer> possibleList = sudokuGrid.getPossibleValue(gx, gy);
					if (possibleList.size() == 2) {
						otherList.add(possibleList);
					}
				}
			}
		}

		return isFind(sudokuGrid, x, y, possibleValue, otherList);
	}

}
