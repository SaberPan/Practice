package com.saber.rule.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.saber.bean.SudokuGrid;
import com.saber.rule.FindRuleOperator;
import com.saber.rule.IFindRule;
import com.saber.service.InputService;
import com.saber.util.PathUtils;

public class BaseTest {

	public void testFind(Class<? extends IFindRule> clazz, String filePath, int x, int y, int expectValue)
			throws Exception {
		SudokuGrid sdkGrid = new InputService().inputByFile(filePath);

		List<Integer> possibleValueList = sdkGrid.getPossibleValue(x, y);
		assertEquals(null, sdkGrid.getValue(x, y));

		IFindRule finder = (IFindRule) clazz.newInstance();
		boolean isFind = finder.find(sdkGrid, x, y, possibleValueList);

		assertEquals(true, isFind);
		assertEquals(expectValue, sdkGrid.getValue(x, y).intValue());
	}

//	 @Test
	public void test() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/RecessivenessExclusiveMethodOnX.xlsx");
		SudokuGrid sdkGrid = new InputService().inputByFile(filePath);
		int x = 8;
		int y = 2;
		int expectValue = 5;
		assertEquals(null, sdkGrid.getValue(x, y));
		boolean isFind = FindRuleOperator.getInstance().find(sdkGrid, x, y);
		assertEquals(true, isFind);
		assertEquals(expectValue, sdkGrid.getValue(x, y).intValue());
	}
}
