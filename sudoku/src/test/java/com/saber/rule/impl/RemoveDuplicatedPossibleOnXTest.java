package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class RemoveDuplicatedPossibleOnXTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/RemoveDuplicatedPossibleOnX.xlsx");
		super.testFind(RemoveDuplicatedPossibleOnX.class, filePath, 6, 4, 4);
	}

}
