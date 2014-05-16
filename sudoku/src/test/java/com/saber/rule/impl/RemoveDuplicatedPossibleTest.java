package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class RemoveDuplicatedPossibleTest extends BaseTest {
	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/RemoveDuplicatedPossible.xlsx");
		super.testFind(RemoveDuplicatedPossible.class, filePath, 7, 4, 3);

		filePath = PathUtils.getPath("com/saber/rule/impl/RemoveDuplicatedPossible2.xlsx");
		super.testFind(RemoveDuplicatedPossible.class, filePath, 6, 3, 5);
	}

}
