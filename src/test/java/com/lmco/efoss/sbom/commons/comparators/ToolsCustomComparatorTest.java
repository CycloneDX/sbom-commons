/*
 * Copyright (c) 2018,2019, 2020, 2021 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package com.lmco.efoss.sbom.commons.comparators;

import java.util.Date;

import org.cyclonedx.model.Tool;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import com.lmco.efoss.sbom.commons.test.utils.TestUtils;
import com.lmco.efoss.sbom.commons.utils.DateUtils;

/**
 * (U) This is the test class for the Tools Custom Comparator, used to see if two tools are the
 * same.
 * 
 * @author wrgoff
 * @since 01 March 2021
 */
public class ToolsCustomComparatorTest
{
	private static final String LOG4J_FILE = "ToolsCustomComparatorAppender.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE,
			"ToolsCustomComparatorTest");
	
	/**
	 * (U) This method tests the equal of two tools.
	 */
	@Test
	public void testCompareToEqual()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String name = "someToolsName";
		String vendor = "Lockheed Martin";
		String version = "1.1.0";
		
		Tool expectedTool = new Tool();
		expectedTool.setName(name);
		expectedTool.setVendor(vendor);
		expectedTool.setVersion(version);
		
		try
		{
			Tool actualTool = new Tool();
			actualTool.setName(name);
			actualTool.setVendor(vendor);
			actualTool.setVersion(version);
			
			ToolsCustomComparator comparator = new ToolsCustomComparator();
			Assert.assertTrue(comparator.equals(expectedTool, actualTool));
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to see if two tools are equal.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
