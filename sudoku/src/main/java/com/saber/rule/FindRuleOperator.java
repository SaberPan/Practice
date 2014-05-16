package com.saber.rule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.saber.bean.SudokuGrid;
import com.saber.util.CommonUtils;

/**
 * @author Saber Pan
 * 
 */
public class FindRuleOperator {

	private List<IFindRule> ruleObjectList = new ArrayList<IFindRule>();

	private FindRuleOperator() {
		try {
			List<String> classList = FileUtils.readLines(new File(ClassLoader.getSystemResource("RulesConfig")
					.getPath()), CommonUtils.DEFAULT_ENCODING);
			for (String clazz : classList) {
				try {
					IFindRule operator = (IFindRule) Class.forName(clazz).newInstance();
					ruleObjectList.add(operator);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class FindRuleOperatorFactory {
		private static FindRuleOperator instance = new FindRuleOperator();
	}

	public static FindRuleOperator getInstance() {
		return FindRuleOperatorFactory.instance;
	}

	public boolean find(SudokuGrid sudokuGrid, int x, int y) throws Exception {
		if (sudokuGrid.getValue(x, y) != null) {
			return false;
		}
		List<Integer> possibleValue = sudokuGrid.getPossibleValue(x, y);
		for (IFindRule ruleOperator : ruleObjectList) {
			boolean isFind = ruleOperator.find(sudokuGrid, x, y, possibleValue);
			if (isFind) {
				return true;
			}
		}
		return false;
	}

}
