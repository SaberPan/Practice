package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

public class ExclusiveMethodTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/ExclusiveMethod.xlsx");
		super.testFind(ExclusiveMethod.class, filePath, 0, 1, 1);
	}

}
