package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.constant.SudokuConstant;
import com.saber.rule.IFindRule;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Saber Pan
 * 
 */
public class PossibleValueRule implements IFindRule {

	private Logger LOG = Logger.getLogger(this.getClass());

	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		boolean isFind = false;
		if (possibleValue.size() == 1) {
			sudokuGrid.setValue(possibleValue.get(0), x, y);
			LOG.info("[" + x + "," + y + "] - " + sudokuGrid.getValue(x, y));
			return true;
		} else {
			Set<Integer> xPossibleValueSet = getAllPossibleValueOnX(sudokuGrid, x, y);
			for (Integer value : possibleValue) {
				if (!xPossibleValueSet.contains(value)) {
					sudokuGrid.setValue(value, x, y);
					LOG.info("[" + x + "," + y + "] - " + sudokuGrid.getValue(x, y));
					return true;
				}
			}

			Set<Integer> yPossibleValueSet = getAllPossibleValueOnY(sudokuGrid, x, y);
			for (Integer value : possibleValue) {
				if (!yPossibleValueSet.contains(value)) {
					sudokuGrid.setValue(value, x, y);
					LOG.info("[" + x + "," + y + "] - " + sudokuGrid.getValue(x, y));
					return true;
				}
			}

			Set<Integer> gridPossibleValueSet = getAllPossibleValue4Grid(sudokuGrid, x, y);
			for (Integer value : possibleValue) {
				if (!gridPossibleValueSet.contains(value)) {
					sudokuGrid.setValue(value, x, y);
					LOG.info("[" + x + "," + y + "] - " + sudokuGrid.getValue(x, y));
					return true;
				}
			}
		}

		return false;
	}

	private Set<Integer> getAllPossibleValueOnX(SudokuGrid sudokuGrid, int exceptX, int exceptY) throws Exception {
		Set<Integer> set = new HashSet<Integer>();
		// On X
		for (int y = 0; y < SudokuConstant.GRID_SIZE; y++) {
			if (y != exceptY) {
				Integer value = sudokuGrid.getValue(exceptX, y);
				if (value == null) {
					set.addAll(sudokuGrid.getPossibleValue(exceptX, y));
				}
			}
		}
		return set;
	}

	private Set<Integer> getAllPossibleValueOnY(SudokuGrid sudokuGrid, int exceptX, int exceptY) throws Exception {
		Set<Integer> set = new HashSet<Integer>();
		// On Y
		for (int x = 0; x < SudokuConstant.GRID_SIZE; x++) {
			if (x != exceptX) {
				Integer value = sudokuGrid.getValue(x, exceptY);
				if (value == null) {
					set.addAll(sudokuGrid.getPossibleValue(x, exceptY));
				}
			}
		}
		return set;
	}

	private Set<Integer> getAllPossibleValue4Grid(SudokuGrid sudokuGrid, int exceptX, int exceptY) throws Exception {
		Set<Integer> set = new HashSet<Integer>();
		for (int y = 0; y < 3; y++) {
			int gy = 3 * (exceptY / 3) + y;
			for (int x = 0; x < 3; x++) {
				int gx = 3 * (exceptX / 3) + x;
				if (gx != exceptX || gy != exceptY) {
					Integer value = sudokuGrid.getValue(gx, gy);
					if (value == null) {
						set.addAll(sudokuGrid.getPossibleValue(gx, gy));
					}
				}
			}
		}
		return set;
	}
}
