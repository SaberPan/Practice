package com.saber.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.saber.constant.SudokuConstant;

/**
 * @author Saber Pan
 * 
 */
public class Grid {

	private Integer[] values = null;

	private boolean isOver = false;

	public Grid() {
		this.values = new Integer[SudokuConstant.GRID_SIZE];
	}

	public void setValue(int value, int x, int y) throws Exception {
		if (value < SudokuConstant.SUDOKU_MIN || value > SudokuConstant.SUDOKU_MAX) {
			throw new Exception("Value must be in the range: " + SudokuConstant.SUDOKU_MIN + " - "
					+ SudokuConstant.SUDOKU_MAX + ": " + value);
		}
		int index = getIndex(x, y);
		this.values[index] = value;
	}

	public Integer getValue(int x, int y) throws Exception {
		int index = getIndex(x, y);
		return this.values[index];
	}

	/***
	 * The other two values on Y are not empty.
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public boolean isNotEmptyOnY(int x, int y) throws Exception {
		for (int i = 0; i < 3; i++) {
			if (x != i && getValue(i, y) == null) {
				return false;
			}
		}
		return true;
	}

	/***
	 * The other two values on X are not empty.
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public boolean isNotEmptyOnX(int x, int y) throws Exception {
		for (int i = 0; i < 3; i++) {
			if (y != i && getValue(x, i) == null) {
				return false;
			}
		}
		return true;
	}

	/***
	 * There is only one value except itself on X.
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public boolean isOnlyOneEmptyOnX(int x, int y) throws Exception {
		List<Boolean> list = new ArrayList<Boolean>();
		for (int i = 0; i < 3; i++) {
			if (y != i) {
				list.add(getValue(x, i) == null);
			}
		}

		if (list.get(0) && !list.get(1)) {
			return true;
		}
		if (!list.get(0) && list.get(1)) {
			return true;
		}
		return false;
	}

	/***
	 * There is only one value except itself on Y.
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public boolean isOnlyOneEmptyOnY(int x, int y) throws Exception {
		List<Boolean> list = new ArrayList<Boolean>();
		for (int i = 0; i < 3; i++) {
			if (x != i) {
				list.add(getValue(i, y) == null);
			}
		}

		if (list.get(0) && !list.get(1)) {
			return true;
		}
		if (!list.get(0) && list.get(1)) {
			return true;
		}
		return false;
	}

	public int findEmptyIndexOnX(int x, int y) throws Exception {
		for (int i = 0; i < 3; i++) {
			if (y != i && getValue(x, i) == null) {
				return i;
			}
		}
		return -1;
	}

	public int findEmptyIndexOnY(int x, int y) throws Exception {
		for (int i = 0; i < 3; i++) {
			if (x != i && getValue(i, y) == null) {
				return i;
			}
		}
		return -1;
	}

	public List<Integer> getPossibleValue(int x, int y) throws Exception {
		List<Integer> allPossibleValue = null;
		Integer value = getValue(x, y);
		if (value != null) {
			allPossibleValue = new ArrayList<Integer>(Arrays.asList(new Integer[] { value }));
		} else {
			allPossibleValue = new ArrayList<Integer>(Arrays.asList(SudokuConstant.SUDOKU_CELL));
			allPossibleValue.removeAll(Arrays.asList(values));
		}
		return allPossibleValue;
	}

	public boolean isFullOnX(int x) throws Exception {
		for (int i = 0; i < 3; i++) {
			if (getValue(x, i) == null) {
				return false;
			}
		}
		return true;
	}

	public boolean isFullOnY(int y) throws Exception {
		for (int i = 0; i < 3; i++) {
			if (getValue(i, y) == null) {
				return false;
			}
		}
		return true;
	}

	public boolean isContain(int value) {
		if (this.values == null) {
			return false;
		}
		for (Integer in : values) {
			if (in != null && in.intValue() == value) {
				return true;
			}
		}
		return false;
	}

	private int getIndex(int x, int y) throws Exception {
		int index = x + y * 3;
		if (index < 0 || index >= values.length) {
			throw new Exception("Wrong coord: [" + x + "," + y + "]");
		}
		return index;
	}

	public boolean isOver() {
		if (isOver) {
			return true;
		}
		for (int i = 0; i < values.length; i++) {
			Integer value = values[i];
			if (value == null) {
				return false;
			}
		}
		isOver = true;
		return isOver;
	}

	public String toString() {
		return ArrayUtils.toString(values);

	}
}
