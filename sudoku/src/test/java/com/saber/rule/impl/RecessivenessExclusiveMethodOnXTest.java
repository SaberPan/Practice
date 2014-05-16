package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class RecessivenessExclusiveMethodOnXTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/ExclusiveMethodOnX.xlsx");
		super.testFind(RecessivenessExclusiveMethodOnX.class, filePath, 7, 5, 2);

		filePath = PathUtils.getPath("com/saber/rule/impl/RecessivenessExclusiveMethodOnX.xlsx");
		super.testFind(RecessivenessExclusiveMethodOnX.class, filePath, 8, 2, 5);
	}

}
