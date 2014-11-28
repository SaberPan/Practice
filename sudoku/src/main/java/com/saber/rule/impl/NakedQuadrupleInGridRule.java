package com.saber.rule.impl;

import com.saber.bean.SudokuGrid;
import com.saber.rule.IFindRule;

import java.util.List;

/**
 *
 * @author: Saber Pan
 */
public class NakedQuadrupleInGridRule extends NakedTriplesInGridRule implements IFindRule {

	@Override
	public boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception {
		super.NAKED_TRIPLES_NUMBER = 4;
		return super.find(sudokuGrid, x, y, possibleValue);
	}
}
