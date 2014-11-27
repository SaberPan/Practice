package com.saber.rule.impl;

import com.saber.util.PathUtils;
import org.junit.Test;

public class IndirectRecessivenessExclusiveOnXTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/IndirectRecessivenessExclusiveOnX.xlsx");
		super.testFind(IndirectRecessivenessExclusiveOnX.class, filePath, 7, 0, 2);
	}
}