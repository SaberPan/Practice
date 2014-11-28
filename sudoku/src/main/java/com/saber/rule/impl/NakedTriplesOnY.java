package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

import java.util.List;

/**
 *
 * @author: Saber Pan
 */
public class NakedTriplesOnY extends BaseAbstract implements IFindRule {

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		return false;
	}
}
