package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class RecessivenessExclusiveMethodOnYTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/ExclusiveMethodOnY.xlsx");
		super.testFind(RecessivenessExclusiveMethodOnY.class, filePath, 5, 7, 2);
	}

}
