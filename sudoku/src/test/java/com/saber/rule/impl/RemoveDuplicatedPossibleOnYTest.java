package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class RemoveDuplicatedPossibleOnYTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/RemoveDuplicatedPossibleOnY.xlsx");
		super.testFind(RemoveDuplicatedPossibleOnY.class, filePath, 0, 2, 1);
	}

}
