package com.saber.rule;

import java.util.List;

import com.saber.bean.SudokuGrid;

/**
 * @author Saber Pan
 * 
 */
public interface IFindRule {

	boolean find(SudokuGrid sudokuGrid, int x, int y, List<Integer> possibleValue) throws Exception;
}
