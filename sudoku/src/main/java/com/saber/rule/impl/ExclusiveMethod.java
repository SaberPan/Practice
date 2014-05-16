package com.saber.rule.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

/**
 * @author Saber Pan
 * 
 */
public class ExclusiveMethod extends BaseAbstract implements IFindRule {

	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		List<List<Integer>> otherList = findPossibleValueInGrid(sudokuGrid, x, y);

		Map<Integer, Integer> countMap = getCountMap(otherList);
		if (countMap.size() < 2) {
			return false;
		}

		List<Integer> duplicateValueList = getDuplicateValueList(countMap);
		if (duplicateValueList.size() < 2) {
			return false;
		}

		for (int i = 0; i < duplicateValueList.size(); i++) {
			Integer value1 = duplicateValueList.get(i);
			if (possibleValue.contains(value1)) {
				continue;
			}
			for (int j = i + 1; j < duplicateValueList.size(); j++) {
				Integer value2 = duplicateValueList.get(j);
				if (possibleValue.contains(value2)) {
					continue;
				}
				List<List<Integer>> twoList = findValueList(otherList, value1, value2);
				if (twoList.size() == 2) {
					Integer value = findValue(possibleValue, twoList.get(0), twoList.get(1));
					if (value != null && countMap.get(value) == 1) {
						LOG.info("[" + x + "," + y + "] - " + value);
						sudokuGrid.setValue(value, x, y);
						return true;
					}
				}
			}
		}

		return false;
	}

	private List<List<Integer>> findPossibleValueInGrid(SudokuGrid sudokuGrid, int x, int y) throws Exception {
		List<List<Integer>> otherList = new ArrayList<List<Integer>>();
		for (int gridY = 0; gridY < 3; gridY++) {
			int gy = 3 * (y / 3) + gridY;
			for (int gridX = 0; gridX < 3; gridX++) {
				int gx = 3 * (x / 3) + gridX;
				if ((gx != x || gy != y) && sudokuGrid.getValue(x, y) == null) {
					List<Integer> possibleList = sudokuGrid.getPossibleValue(gx, gy);
					otherList.add(possibleList);
				}
			}
		}
		return otherList;
	}

	private Integer findValue(List<Integer> possibleValue, List<Integer> list, List<Integer> list2) {
		for (Integer value : possibleValue) {
			if (list.contains(value) || list2.contains(value)) {
				return value;
			}
		}
		return null;
	}

	private List<Integer> getDuplicateValueList(Map<Integer, Integer> map) {
		List<Integer> duplicateValueList = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 2) {
				duplicateValueList.add(entry.getKey());
			}
		}
		return duplicateValueList;
	}

	private List<List<Integer>> findValueList(List<List<Integer>> otherList, Integer value1, Integer value2) {
		List<List<Integer>> twoList = new ArrayList<List<Integer>>();
		for (List<Integer> list : otherList) {
			if (list.contains(value1) && list.contains(value2)) {
				twoList.add(list);
			}
		}
		return twoList;
	}

}
