package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * @author Saber Pan
 * 
 */
public abstract class BaseAbstract {

	protected Logger LOG = Logger.getLogger(this.getClass());

	public boolean isFind(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue, List<List<Integer>> otherList) throws Exception {
		Set<List<Integer>> set = new HashSet<List<Integer>>(otherList);
		if (otherList.size() < 2 || otherList.size() == set.size()) {
			return false;
		}

		Map<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();
		for (List<Integer> pl : otherList) {
			Integer count = map.get(pl);
			if (count == null) {
				count = 0;
			}
			map.put(pl, count + 1);
		}

		for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 2) {
				List<Integer> cloneList = ObjectUtils.clone(possibleValue);
				cloneList.removeAll(entry.getKey());
				if (cloneList.size() == 1) {
					int value = cloneList.get(0);
					LOG.info(": [" + x + "," + y + "] - " + value);
					sudokuGrid.setValue(value, x, y);
					return true;
				}
			}
		}
		return false;
	}

	protected Map<Integer, Integer> getCountMap(List<List<Integer>> otherList) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (List<Integer> list : otherList) {
			for (Integer value : list) {
				Integer count = map.get(value);
				if (count == null) {
					count = 0;
				}
				map.put(value, count + 1);
			}
		}
		return map;
	}

	protected boolean isExclusiveValueOnY(SudokuGrid sudokuGrid, int x, int y, int possibleValue) throws Exception {
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
					List<Integer> otherGridPossibleValues = sudokuGrid.getPossibleValue(tempX, tempY);
					if (otherGridPossibleValues.contains(possibleValue)) {
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

	protected boolean isExclusiveValueOnX(SudokuGrid sudokuGrid, int x, int y, int possibleValue) throws Exception {
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
					List<Integer> otherGridPossibleValues = sudokuGrid.getPossibleValue(tempX, tempY);
					if (otherGridPossibleValues.contains(possibleValue)) {
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
