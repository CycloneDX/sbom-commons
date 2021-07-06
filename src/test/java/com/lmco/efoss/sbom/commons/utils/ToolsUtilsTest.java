/*
 * Copyright (c) 2018,2019, 2020, 2021 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package com.lmco.efoss.sbom.commons.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cyclonedx.model.Tool;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import com.lmco.efoss.sbom.commons.test.utils.TestUtils;

/**
 * (U) This class is used to test the Tools Utilities.
 * 
 * @author wrgoff
 * @since 01 March 2021
 */
public class ToolsUtilsTest
{
	public ToolsUtils toolsUtils = new ToolsUtils();
	
	private static final String LOG4J_FILE = "ToolsUtilsAppender.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE, "ToolsUtilsTest");
	
	/**
	 * (U) Used to test the adding of tools (no duplicates) to the list of complete tools.
	 */
	@Test
	public void testAddLists()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String name = "someToolsName";
		String vendor = "Lockheed Martin";
		String version = "1.1.0";
		
		List<Tool> expectedToolsList = new ArrayList<Tool>();
		Tool expectedTool = new Tool();
		expectedTool.setName(name);
		expectedTool.setVendor(vendor);
		expectedTool.setVersion(version);
		expectedToolsList.add(expectedTool);
		try
		{
			List<Tool> toolsList = new ArrayList<Tool>();
			Tool tool = new Tool();
			tool.setName(name);
			tool.setVendor(vendor);
			tool.setVersion(version);
			toolsList.add(tool);
			
			Tool tool2 = new Tool();
			tool2.setName(name);
			tool2.setVendor(vendor);
			tool2.setVersion(version);
			toolsList.add(tool2);
			
			List<Tool> completeList = new ArrayList<Tool>();
			completeList = ToolsUtils.addUniqueTools(completeList, toolsList);
			
			Assert.assertEquals(expectedToolsList, completeList);
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to see if two Lists of tools " +
					"are equal.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This method is used to test the toString method in the ToolsUtils.
	 */
	@Test
	public void toolToString()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		try
		{
			String name = "someToolsName";
			String vendor = "Lockheed Martin";
			String version = "1.1.0";
			
			Tool tool = new Tool();
			tool.setName(name);
			tool.setVendor(vendor);
			tool.setVersion(version);
			
			String expectedStringWithoutTabs = "Tool Name:someToolsName\n" +
					"Tool Vendor: Lockheed Martin\n" +
					"Tool Version: 1.1.0\n";
			
			String actualStringWithoutTabs = ToolsUtils.toString(tool);
			
			if (expectedStringWithoutTabs.equals(actualStringWithoutTabs))
				watcher.getLogger().info("Got Expected String from ToolUtils.toString(tool)!");
			else
			{
				String diff = StringUtils.difference(expectedStringWithoutTabs,
						actualStringWithoutTabs);
				watcher.getLogger().warn("Failed to generate the expected String from " +
						"ToolUtils.toString(tool): \n" +
						"Difference: " + diff);
			}
			Assert.assertTrue(expectedStringWithoutTabs.equals(actualStringWithoutTabs));
			
			String expectedStringWithTabs = "	Tool Name:someToolsName\n" +
					"	Tool Vendor: Lockheed Martin\n" +
					"	Tool Version: 1.1.0\n";
			
			String actualStringWithTabs = ToolsUtils.toString(tool, "	");
			
			if (actualStringWithTabs.equals(expectedStringWithTabs))
				watcher.getLogger().info("Got Expected String from " +
						"ToolUtils.toString(tool, \"	\")!");
			else
			{
				String diff = StringUtils.difference(expectedStringWithTabs,
						actualStringWithTabs);
				watcher.getLogger().warn("Failed to generate the expected String from " +
						"ToolUtils.toString(tool, \"	\"): \n" +
						"Difference: " + diff);
			}
			Assert.assertTrue(expectedStringWithTabs.equals(actualStringWithTabs));
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting ToolUtils.toString.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
