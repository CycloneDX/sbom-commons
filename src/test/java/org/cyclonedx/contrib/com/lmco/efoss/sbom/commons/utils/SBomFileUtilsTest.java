/*
 * Copyright (c) 2018,2019 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.TestUtils;


/**
 * (U) This Unit test contains the test cases for reading an Software Bill Of
 * Materials from a file.
 * 
 * @author wrgoff
 * @since 9 July 2020
 */
public class SBomFileUtilsTest
{
	public SBomFileUtils fileUtils = new SBomFileUtils();
	
	private static final String LOG4J_FILE = "SBomFileUtilsAppender.xml";

	private static final String fileName = "OrgSbom.xml";
	private static final String jsonFileName = "OrgSbom.json";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE,
			"SBomFileUtilsTest");
	
	/**
	 * (U) This test case test to make sure we are reading the correct number
	 * of Components in a SBom file.
	 */
	@Test
	public void testReadingSBomComponentsFromInputStream()
	{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());		
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			Bom sbom = SBomFileUtils.processInputStream(stream);
			
			List<Component> components = sbom.getComponents();
			Assert.assertEquals(75, components.size());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"process bom file (" + fileName + ").";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case test to make sure we are reading the correct number
	 * of Components in a SBom file.
	 */
	@Test
	public void testReadingSBomComponentsFromJsonInputStream()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		try (InputStream stream = getClass().getResourceAsStream("/" + jsonFileName))
		{
			Bom sbom = SBomFileUtils.processInputStream(stream);
			
			List<Component> components = sbom.getComponents();
			Assert.assertEquals(75, components.size());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"process bom file (" + fileName + ").";
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
	public void testReadingSBomComponentsFromFile()
	{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		try
		{
			File file = new File(getClass().getResource("/" + fileName).getFile());
			
			Bom sbom = SBomFileUtils.processFile(file);
			
			List<Component> components = sbom.getComponents();
			Assert.assertEquals(75, components.size());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"process bom file (" + fileName + ").";
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
	public void testEmptyBomFile()
	{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		try
		{
			Exception exception = Assert.assertThrows(SBomCommonsException.class, () ->
			{
				File file = new File(getClass().getResource("/badBom.xml").getFile());
				SBomFileUtils.processFile(file);
			});
			
			String expectedMessage = "File contents are NOT either valid JSon, or valid XML!";
			String actualMessage = exception.getMessage();
			
			watcher.getLogger().debug("Actual Message: " + actualMessage);
			
			Assert.assertTrue(actualMessage.contains(expectedMessage));
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case test to make sure we are got the expected number of properties from a
	 * component within the SBom.
	 */
	@Test
	public void testReadingSBomComponentsWithPropertiesFromInputStream()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String fileName = "23March2021.xml";
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			Bom sbom = SBomFileUtils.processInputStream(stream);
			
			Component log4jComponent = null;
			for (Component comp : sbom.getComponents())
			{
				if (comp.getName().equalsIgnoreCase("log4j"))
				{
					log4jComponent = comp;
					break;
				}
			}
			int expectedPropertySize = 3;
			
			if (expectedPropertySize == log4jComponent.getProperties().size())
				watcher.getLogger().debug("Got the expected number of properties (3)!");
			else
				watcher.getLogger().warn("Expected to find " + expectedPropertySize +
						" properties.  But got " + log4jComponent.getProperties().size());
			
			Assert.assertEquals(expectedPropertySize, log4jComponent.getProperties().size());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"process bom file (" + fileName + ").";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
