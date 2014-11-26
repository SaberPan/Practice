package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

import java.util.*;

/**
 * @author Saber Pan
 * 
 */
public class RecessivenessExclusiveMethodOnX extends BaseAbstract implements IFindRule {

	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		if (possibleValue.size() != 2) {
			return false;
		}

		List<List<Integer>> otherList = new ArrayList<List<Integer>>();
		Map<List<Integer>, int[]> coordinateMap = new HashMap<List<Integer>, int[]>();
		for (int gridY = 0; gridY < 3; gridY++) {
			int gy = 3 * (y / 3) + gridY;
			for (int gridX = 0; gridX < 3; gridX++) {
				int gx = 3 * (x / 3) + gridX;
				if ((gx != x || gy != y) && sudokuGrid.getValue(x, y) == null) {
					List<Integer> possibleList = sudokuGrid.getPossibleValue(gx, gy);
					otherList.add(possibleList);
					coordinateMap.put(possibleList, new int[] { gx, gy });
				}
			}
		}

		Map<Integer, Integer> countMap = getCountMap(otherList);
		for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
			Integer value = entry.getKey();
			Integer count = entry.getValue();
			if (count == 1 && possibleValue.contains(value)) {
				List<Integer> pl = findPossibleValue(value, otherList);
				int[] coordinate = coordinateMap.get(pl);
				if (coordinate[0] != x && isImpossible(sudokuGrid, coordinate[0], coordinate[1], value)) {
					LOG.info("[" + x + "," + y + "] - " + value);
					sudokuGrid.setValue(value, x, y);
					return true;
				}
			}
		}

		return false;
	}

	public boolean isImpossible(SudokuGrid sudokuGrid, int x, int y, Integer possibleValue) throws Exception {
		for (int gridY1 = 0; gridY1 < 3; gridY1++) {
			if (gridY1 == (y / 3)) {
				continue;
			}

			// other grid
			Set<Integer> sameYSet = new HashSet<Integer>();
			Set<Integer> notSameYSet = new HashSet<Integer>();
			for (int gridX = 0; gridX < 3; gridX++) {
				int gx = 3 * (x / 3) + gridX;
				for (int subY = 0; subY < 3; subY++) {
					int gy = 3 * gridY1 + subY;
					if (sudokuGrid.getValue(gx, gy) == null) {
						List<Integer> possibleList = sudokuGrid.getPossibleValue(gx, gy);
						if (x == gx) {
							sameYSet.addAll(possibleList);
						} else {
							notSameYSet.addAll(possibleList);
						}
					}
				}
			}

			if (isImpossible(possibleValue, sameYSet, notSameYSet)) {
				return true;
			}
		}
		return false;
	}

	private List<Integer> findPossibleValue(Integer key, List<List<Integer>> otherList) {
		for (List<Integer> pl : otherList) {
			if (pl.contains(key)) {
				return pl;
			}
		}
		return null;
	}

	/***
	 * sameYSet包含value, notSameYSet不包含value.
	 * 
	 * @param value
	 * @param sameYSet
	 * @param notSameYSet
	 * @return
	 */
	private boolean isImpossible(Integer value, Set<Integer> sameYSet, Set<Integer> notSameYSet) {
		if (sameYSet.contains(value) && !notSameYSet.contains(value)) {
			return true;
		}
		return false;
	}

}
