package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class NakedTriplesInGridRuleTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/NakedTriples.xlsx");
		super.testFind(NakedTriplesInGridRule.class, filePath, 5, 2, 4);
	}
}