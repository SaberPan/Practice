package com.saber.rule.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;

import com.saber.bean.SudokuGrid;

/**
 * @author Saber Pan
 * 
 */
public abstract class BaseAbstract {

	protected Logger LOG = Logger.getLogger(this.getClass());

	public boolean isFind(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue,
			List<List<Integer>> otherList) throws Exception {
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
}
