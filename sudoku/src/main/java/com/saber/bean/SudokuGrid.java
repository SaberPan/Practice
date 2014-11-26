/**
 * 
 */
package com.saber.bean;

import com.saber.constant.SudokuConstant;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Saber Pan
 * 
 */
public class SudokuGrid implements Cloneable {

	private static Logger LOG = Logger.getLogger(SudokuGrid.class);
	private Grid[] grids = new Grid[SudokuConstant.GRID_SIZE];
	private List<String> inputList = new ArrayList<String>();

	public SudokuGrid() {
	}

	public Grid getGrid(int x, int y) throws Exception {
		int index = getIndex(x / 3, y / 3);
		if (grids[index] == null) {
			grids[index] = new Grid();
		}
		return grids[index];
	}

	public List<Grid> getAnotherTwoGridOnY(int x, int y) throws Exception {
		int gx = x / 3;
		List<Grid> list = new ArrayList<Grid>();
		for (int i = 0; i < 3; i++) {
			if (gx != i) {
				list.add(getGrid(i * 3, y));
			}
		}
		return list;
	}

	public List<Grid> getAnotherTwoGridOnX(int x, int y) throws Exception {
		int gy = y / 3;
		List<Grid> list = new ArrayList<Grid>();
		for (int i = 0; i < 3; i++) {
			if (gy != i) {
				list.add(getGrid(x, i * 3));
			}
		}
		return list;
	}

	public void setValue(int value, int x, int y) throws Exception {
		getGrid(x, y).setValue(value, x % 3, y % 3);
	}

	public void setIsInput(int x, int y) {
		inputList.add(x + "_" + y);
	}

	public boolean isInput(int x, int y) {
		return inputList.contains(x + "_" + y);
	}

	public Integer getValue(int x, int y) throws Exception {
		return getGrid(x, y).getValue(x % 3, y % 3);
	}

	private int getIndex(int x, int y) throws Exception {
		int index = x + y * 3;
		if (index < 0 || index >= grids.length) {
			throw new Exception("Wrong coord: [" + x + "," + y + "]");
		}
		return index;
	}

	public List<Integer> getPossibleValue(int x, int y) throws Exception {
		List<Integer> possibleValue = getGrid(x, y).getPossibleValue(x % 3, y % 3);
		if (possibleValue.size() > 1) {
			removeExistValue_X(x, y, possibleValue);
			removeExistValue_Y(x, y, possibleValue);
		}

		return possibleValue;
	}

	private void removeExistValue_X(int x, int y, List<Integer> possibleValue) throws Exception {
		for (int i = 0; i < SudokuConstant.GRID_SIZE; i++) {
			if (i != x) {
				Integer value = getValue(i, y);
				if (value != null) {
					possibleValue.remove(value);
				}
			}
		}
	}

	private void removeExistValue_Y(int x, int y, List<Integer> possibleValue) throws Exception {
		for (int i = 0; i < SudokuConstant.GRID_SIZE; i++) {
			if (i != y) {
				Integer value = getValue(x, i);
				if (value != null) {
					possibleValue.remove(value);
				}
			}
		}
	}

	public boolean isOver() {
		for (Grid grid : grids) {
			if (grid == null || !grid.isOver()) {
				return false;
			}
		}
		return true;
	}

	public boolean isRight() throws Exception {
		return isRightOnX() && isRightOnY();
	}

	private boolean isRightOnX() throws Exception {
		for (int x = 0; x < SudokuConstant.GRID_SIZE; x++) {
			Set<Integer> set = new HashSet<Integer>();
			for (int y = 0; y < SudokuConstant.GRID_SIZE; y++) {
				Integer value = getValue(x, y);
				if (value == null) {
					return false;
				}

				if (set.contains(value)) {
					LOG.error("Same value on X: " + value);
					return false;
				} else {
					set.add(value);
				}
			}
		}

		return true;
	}

	private boolean isRightOnY() throws Exception {
		for (int y = 0; y < SudokuConstant.GRID_SIZE; y++) {
			Set<Integer> set = new HashSet<Integer>();
			for (int x = 0; x < SudokuConstant.GRID_SIZE; x++) {
				Integer value = getValue(x, y);
				if (value == null) {
					return false;
				}

				if (set.contains(value)) {
					LOG.error("Same value on Y: " + value);
					return false;
				} else {
					set.add(value);
				}
			}
		}

		return true;
	}

	/***
	 * There are the <i>value</i> on the other two X.
	 * 
	 * @param value
	 * @param x
	 * @return
	 * @throws Exception
	 */
	public boolean isContainValueOnX(int value, int x) throws Exception {
		boolean isFind = true;
		int gx = x % 3;
		for (int i = 0; i < 3; i++) {
			if (gx != i) {
				int anotherX = 3 * (x / 3) + i;
				boolean isContain = false;
				for (int j = 0; j < SudokuConstant.GRID_SIZE; j++) {
					Integer xValue = getValue(anotherX, j);
					if (xValue != null && value == xValue) {
						isContain = true;
						break;
					}
				}
				isFind = isFind & isContain;
				if (!isFind) {
					break;
				}
			}
		}
		return isFind;
	}

	/***
	 * There are the <i>value</i> on the other two Y.
	 * 
	 * @param value
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public boolean isContainValueOnY(int value, int y) throws Exception {
		boolean isFind = true;
		int gy = y % 3;
		for (int i = 0; i < 3; i++) {
			if (gy != i) {
				int anotherY = 3 * (y / 3) + i;
				boolean isContain = false;
				for (int j = 0; j < SudokuConstant.GRID_SIZE; j++) {
					Integer yValue = getValue(j, anotherY);
					if (yValue != null && value == yValue) {
						isContain = true;
						break;
					}
				}
				isFind = isFind & isContain;
				if (!isFind) {
					break;
				}
			}
		}
		return isFind;
	}

	/***
	 * There is the <i>value</i> on the empty X.
	 * 
	 * @param value
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public boolean isContainValueOnEmptyX(int value, int x, int y) throws Exception {
		Grid grid = getGrid(x, y);
		int gx = x % 3;
		int gy = y % 3;

		int emptyIndex = grid.findEmptyIndexOnY(gx, gy);
		int anotherX = 3 * (x / 3) + emptyIndex;
		boolean isContain = false;
		for (int i = 0; i < SudokuConstant.GRID_SIZE; i++) {
			Integer xValue = getValue(anotherX, i);
			if (xValue != null && value == xValue) {
				isContain = true;
				break;
			}
		}
		return isContain;
	}

	/***
	 * There is the <i>value</i> on the empty Y.
	 * 
	 * @param value
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	public boolean isContainValueOnEmptyY(int value, int x, int y) throws Exception {
		Grid grid = getGrid(x, y);
		int gx = x % 3;
		int gy = y % 3;

		int emptyIndex = grid.findEmptyIndexOnX(gx, gy);

		int anotherY = 3 * (y / 3) + emptyIndex;
		boolean isContain = false;
		for (int i = 0; i < SudokuConstant.GRID_SIZE; i++) {
			Integer xValue = getValue(i, anotherY);
			if (xValue != null && value == xValue) {
				isContain = true;
				break;
			}
		}
		return isContain;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Integer value = getValue(j, i);
					if (value != null) {
						sb.append(value);
					}
					sb.append(",");
				}
				sb.deleteCharAt(sb.length() - 1).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
