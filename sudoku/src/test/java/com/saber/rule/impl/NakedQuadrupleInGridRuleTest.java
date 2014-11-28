package com.saber.rule.impl;

import com.saber.util.PathUtils;
import org.junit.Test;

public class NakedQuadrupleInGridRuleTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/NakedQuadrupleInGridRule.xlsx");
		super.testFind(NakedQuadrupleInGridRule.class, filePath, 5, 2, 1);
	}
}