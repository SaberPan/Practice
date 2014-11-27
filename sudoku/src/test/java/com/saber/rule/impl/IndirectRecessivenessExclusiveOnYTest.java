package com.saber.rule.impl;

import com.saber.util.PathUtils;
import org.junit.Test;

public class IndirectRecessivenessExclusiveOnYTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/IndirectRecessivenessExclusiveOnY.xlsx");
		super.testFind(IndirectRecessivenessExclusiveOnY.class, filePath, 0, 7, 2);
	}
}