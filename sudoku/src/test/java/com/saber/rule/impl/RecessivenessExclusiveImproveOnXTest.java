/**
 * Class Name: RecessivenessExclusiveImproveOnXTest
 * Package Name: com.saber.rule.impl
 * File Name: RecessivenessExclusiveImproveOnXTest.java
 * Revision: 1.0
 * Date : 2014-11-25 下午9:01:52   
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
public class RecessivenessExclusiveImproveOnXTest extends BaseTest {

	@Test
	public void testFind() throws Exception {
		String filePath = PathUtils.getPath("com/saber/rule/impl/RecessivenessExclusiveImproveOnX.xlsx");
		super.testFind(RecessivenessExclusiveImproveOnX.class, filePath, 7, 1, 7);
	}

}
