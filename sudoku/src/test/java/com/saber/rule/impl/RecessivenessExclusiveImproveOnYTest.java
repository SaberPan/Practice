/**
 * Class Name: RecessivenessExclusiveImproveOnYTest
 * Package Name: com.saber.rule.impl
 * File Name: RecessivenessExclusiveImproveOnYTest.java
 * Revision: 1.0
 * Date : 2014-11-25 下午9:03:05   
 * 
 * ==================================================================  
 * Copyright (c) 2003-2014 Morningstar, Inc. All Rights Reserved.
 * ==================================================================
 */

package com.saber.rule.impl;

import org.junit.Test;

import com.saber.util.PathUtils;

/**
 * The class used...
 * 
 * @author: Saber Pan
 */
public class RecessivenessExclusiveImproveOnYTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/RecessivenessExclusiveImproveOnY.xlsx");
		super.testFind(RecessivenessExclusiveImproveOnY.class, filePath, 1, 7, 7);
	}

}
