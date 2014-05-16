package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class PossibleValueRuleTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/PossibleValueRule.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 5, 3, 6);

		filePath = PathUtils.getPath("com/saber/rule/impl/PossibleValueRule2.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 5, 3, 6);

		filePath = PathUtils.getPath("com/saber/rule/impl/PossibleValueRule3.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 5, 3, 2);

		filePath = PathUtils.getPath("com/saber/rule/impl/PossibleValueRule4.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 4, 0, 1);

		filePath = PathUtils.getPath("com/saber/rule/impl/AllCheckOnXY.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 3, 3, 2);

		filePath = PathUtils.getPath("com/saber/rule/impl/OneEmptyOnX.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 3, 3, 3);

		filePath = PathUtils.getPath("com/saber/rule/impl/OneEmptyOnY.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 3, 3, 5);

		filePath = PathUtils.getPath("com/saber/rule/impl/TwoNotEmptyOnX.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 3, 3, 7);

		filePath = PathUtils.getPath("com/saber/rule/impl/TwoNotEmptyOnY.xlsx");
		super.testFind(PossibleValueRule.class, filePath, 3, 3, 6);
	}

}
