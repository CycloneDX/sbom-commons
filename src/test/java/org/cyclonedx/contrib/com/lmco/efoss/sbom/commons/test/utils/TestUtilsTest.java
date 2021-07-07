/*
 * Copyright (c) 2018,2019 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils;

import java.util.Date;
import java.util.List;

import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.utils.DateUtils;

/**
 * (U) This class contains tests for the Test utils.
 * 
 * @author wrgoff
 * @since 9 July 2020
 */
public class TestUtilsTest
{
	public TestUtils testUtils = new TestUtils();
	
	private static final String LOG4J_FILE = "TestUtilsAppender.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE, "TestUtilsTest");
	
	/**
	 * (U) This test case test to make sure we are reading the correct number of Components in a
	 * SBom file.
	 */
	@Test
	public void testSbomReadFromFile()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
	
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String fileName = "OrgSbom.json";
	
		try
		{
			Bom orgBom = testUtils.readSbomFile(fileName, watcher.getLogger());
			List<Component> components = orgBom.getComponents();
			
			if (components.size() == 75)
				watcher.getLogger().debug("Test Utils readSbomFile successfully read the sbom " +
						"from a file!");
			else
				watcher.getLogger().warn("Test Utils readSbomFile failed to read in the correct " +
						"number of components from the Resource file (" + fileName +
						") from a file!");
			
			Assert.assertEquals(75, components.size());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to read an SBom using the " +
					"Test Utils readSbomFile!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case test to make sure we are reading the correct number of Components in a
	 * SBom file.
	 */
	@Test
	public void testSbomReadFromFileAbsolutePath()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
	
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String fileName = "./src/test/resources/OrgSbom.json";
	
		try
		{
			Bom orgBom = testUtils.readSbomFileFromAbsolutePath(fileName,  watcher.getLogger());
			List<Component> components = orgBom.getComponents();
			
			if (components.size() == 75)
				watcher.getLogger().debug("Test Utils readSbomFile successfully read the sbom " +
						"from a file!");
			else
				watcher.getLogger().warn("Test Utils readSbomFile failed to read in the correct " +
						"number of components from the Resource file (" + fileName +
						") from a file!");
			
			Assert.assertEquals(75, components.size());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to read an SBom using the " +
					"Test Utils readSbomFile!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
