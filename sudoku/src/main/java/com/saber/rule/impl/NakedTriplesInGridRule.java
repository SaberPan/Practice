package com.saber.rule.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

import java.util.List;
import java.util.Set;

/**
 *
 * @author: Saber Pan
 */
public class NakedTriplesInGridRule extends BaseAbstract implements IFindRule {

	int NAKED_TRIPLES_NUMBER = 3;

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		List<List<Integer>> otherPossibleValueLists = getOtherPossibleValueInGrid(sudokuGrid, x, y);
		if (otherPossibleValueLists.size() < NAKED_TRIPLES_NUMBER) {
			return false;
		}

		List<Integer> initList = Lists.newArrayList();
		List<List<Integer>> allIndexList = combination(0, initList, otherPossibleValueLists.size(), NAKED_TRIPLES_NUMBER);
		for (List<Integer> indexList : allIndexList) {
			Set<Integer> combinePossibleValue = combinePossibleValue(indexList, otherPossibleValueLists);
			if (combinePossibleValue.size() == NAKED_TRIPLES_NUMBER) {
				List<Integer> leftValue = remove(possibleValue, combinePossibleValue);
				if (leftValue.size() == 1) {
					int value = leftValue.get(0);
					LOG.info("[" + x + "," + y + "] - " + value);
					sudokuGrid.setValue(value, x, y);
					return true;
				}
			}
		}

		return false;
	}

	private List<List<Integer>> getOtherPossibleValueInGrid(SudokuGrid sudokuGrid, int x, int y) throws Exception {
		List<List<Integer>> otherPossibleValueLists = Lists.newArrayList();
		for (int xIndex = 0; xIndex < 3; xIndex++) {
			int gridX = 3 * (x / 3) + xIndex;
			for (int yIndex = 0; yIndex < 3; yIndex++) {
				int gridY = 3 * (y / 3) + yIndex;
				if (gridX == x && y == gridY) {
					continue;
				}
				if (sudokuGrid.getValue(gridX, gridY) != null) {
					continue;
				}
				List<Integer> otherPossibleValueList = sudokuGrid.getPossibleValue(gridX, gridY);
				if (otherPossibleValueList.size() <= NAKED_TRIPLES_NUMBER) {
					otherPossibleValueLists.add(otherPossibleValueList);
				}
			}
		}
		return otherPossibleValueLists;
	}

	private List<Integer> remove(List<Integer> possibleValue, Set<Integer> combinePossibleValue) {
		List<Integer> leftValue = Lists.newArrayList();
		for (Integer value : possibleValue) {
			if (!combinePossibleValue.contains(value)) {
				leftValue.add(value);
			}
		}
		return leftValue;
	}

	private Set<Integer> combinePossibleValue(List<Integer> indexList, List<List<Integer>> otherPossibleValueLists) {
		Set<Integer> combinePossibleValue = Sets.newHashSet();
		for (Integer index : indexList) {
			combinePossibleValue.addAll(otherPossibleValueLists.get(index));
		}
		return combinePossibleValue;
	}

	private static List<List<Integer>> combination(int startIndex, List<Integer> list, int n, int m) {
		List<List<Integer>> allList = Lists.newArrayList();
		if (m == 0) {
			allList.add(list);
			return allList;
		}
		if (startIndex == n) {
			return allList;
		}
		List<Integer> list2 = Lists.newArrayList(list);
		list2.add(startIndex);

		List<List<Integer>> subList1 = combination(startIndex + 1, list2, n, m - 1);
		List<List<Integer>> subList2 = combination(startIndex + 1, list, n, m);

		allList.addAll(subList1);
		allList.addAll(subList2);

		return allList;
	}
}
