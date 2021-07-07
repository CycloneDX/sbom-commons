/*
 * Copyright (c) 2018,2019, 2020, 2021 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.utils;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;
import org.cyclonedx.model.Component.Type;
import org.cyclonedx.model.Metadata;
import org.cyclonedx.model.Tool;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.comparators.ComponentComparator;
import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.comparators.ToolsCustomComparator;
import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.TestUtils;

/**
 * (U) Unit tests for the ComponentUtilities.
 * 
 * @author wrgoff
 * @since 14 April 2021
 */
public class ComponentUtilitiesTest
{
	private static final String LOG4J_FILE = "ComponentUtilsAppender.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE,
			"ComponentUtilitiesTest");
	
	/**
	 * (U) This test method is used to test the building of the outer component. In this case we are
	 * retaining the metadata component, but making sure we have added a new tool.
	 */
	@Test
	public void addNewOuterComponentTest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String fileName = "smallBom.xml";
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			Bom sbom = SBomFileUtils.processInputStream(stream);
			
			Component expectedComponent = sbom.getMetadata().getComponent();
			List<Tool> orgTools = sbom.getMetadata().getTools();
			
			String toolName = "testTool";
			String toolVersion = "1.1.2";
			
			ComponentUtilities.addNewOuterComponent(sbom, toolName, toolVersion);
			
			Metadata metadata = sbom.getMetadata();
			Component actualComponent = metadata.getComponent();
			
			ComponentComparator comparator = new ComponentComparator();
			
			if (comparator.equals(expectedComponent, actualComponent))
				watcher.getLogger().info("Successfuly retained the original Metadata Component!");
			else
				watcher.getLogger().warn("Failed to retain the original Metadata Component:\n");
			
			Assert.assertTrue(comparator.equals(expectedComponent, actualComponent));
			
			List<Tool> tools = metadata.getTools();
			if ((orgTools.size() + 1) == tools.size())
				watcher.getLogger().info("Got the correct number of Tools (original plus one).");
			else
				watcher.getLogger().warn("Did NOT get the expected number of Tools." +
						"\n	Expected: " + (orgTools.size() + 1) +
						"\n	Got: " + tools.size());
			
			Assert.assertEquals((orgTools.size() + 1), tools.size());
			
			Tool expectedTool = new Tool();
			expectedTool.setName(toolName);
			expectedTool.setVendor("Lockheed Martin");
			expectedTool.setVersion(toolVersion);
			
			Tool actualTool = tools.get(0);
			ToolsCustomComparator toolComparator = new ToolsCustomComparator();
			
			if (toolComparator.equals(expectedTool, actualTool))
				watcher.getLogger().info("Created Expected Tool!");
			else
				watcher.getLogger().warn("Did NOT create the expected tool." +
						"\n	Expected: " + ToolsUtils.toString(expectedTool, "		") + "\n" +
						"\n	Got: " + ToolsUtils.toString(actualTool, "		"));
			
			Assert.assertTrue(toolComparator.equals(expectedTool, actualTool));
		}
		catch (SBomCommonsException sbomE)
		{
			String error = "Unexpected error occurred while attempting to read SWBom from " +
					"input stream!";
			watcher.getLogger().error(error, sbomE);
			Assert.fail(error + sbomE.getStackTrace());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting create a new Outer " +
					"empty component (with tool).";
			watcher.getLogger().error(error, e);
			Assert.fail(error + e.getStackTrace());
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test method is used to test the building of a null outer Component of an SBom.
	 */
	@Test
	public void addNewOuterComponentTest2()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());

		String fileName = "smallBom.xml";
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			Bom sbom = SBomFileUtils.processInputStream(stream);
			
			List<Tool> orgTools = sbom.getMetadata().getTools();
			
			String toolName = "testTool";
			String toolVersion = "1.1.2";
			
			String compName = "newComponent";
			String compGroup = "com.lmco.efoss";
			String compVersion = "1.5.1";
			Type compType = Component.Type.CONTAINER;

			Component expectedComponent = new Component();
			expectedComponent.setName(compName);
			expectedComponent.setGroup(compGroup);
			expectedComponent.setVersion(compVersion);
			expectedComponent.setType(compType);
			
			ComponentUtilities.addNewOuterComponent(sbom, toolName, toolVersion, compName, 
					compGroup, compVersion, compType.toString());
			
			Metadata metadata = sbom.getMetadata();
			Component actualComponent = metadata.getComponent();
			
			ComponentComparator comparator = new ComponentComparator();
			
			if (comparator.equals(expectedComponent, actualComponent))
				watcher.getLogger().info("Successfuly created a new Metadata Component!");
			else
				watcher.getLogger().warn("Failed to create the new Metadata Component:" +
						"\n	Expected: " + ComponentUtilities.toString(expectedComponent, "		") +
						"\n	Actual: " + ComponentUtilities.toString(actualComponent, "		"));
			
			Assert.assertTrue(comparator.equals(expectedComponent, actualComponent));
			
			List<Tool> tools = metadata.getTools();
			if ((orgTools.size() + 1) == tools.size())
				watcher.getLogger().info("Got the correct number of Tools (original plus one).");
			else
				watcher.getLogger().warn("Did NOT get the expected number of Tools." +
						"\n	Expected: " + (orgTools.size() + 1) +
						"\n	Got: " + tools.size());
			
			Assert.assertEquals((orgTools.size() + 1), tools.size());
			
			Tool expectedTool = new Tool();
			expectedTool.setName(toolName);
			expectedTool.setVendor("Lockheed Martin");
			expectedTool.setVersion(toolVersion);
			
			Tool actualTool = tools.get(0);
			ToolsCustomComparator toolComparator = new ToolsCustomComparator();
			
			if (toolComparator.equals(expectedTool, actualTool))
				watcher.getLogger().info("Created Expected Tool!");
			else
				watcher.getLogger().warn("Did NOT create the expected tool." +
						"\n	Expected: " + ToolsUtils.toString(expectedTool, "		") + "\n" +
						"\n	Got: " + ToolsUtils.toString(actualTool, "		"));
			
			Assert.assertTrue(toolComparator.equals(expectedTool, actualTool));
		}
		catch (SBomCommonsException sbomE)
		{
			String error = "Unexpected error occurred while attempting to read SWBom from " +
					"input stream!";
			watcher.getLogger().error(error, sbomE);
			Assert.fail(error);
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting create a new Outer " +
					"empty component (with tool).";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test method is used to test the building of a null outer Component of an SBom.
	 * Default Type in the Component.
	 */
	@Test
	public void addNewOuterComponentTest3()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String fileName = "smallBom.xml";
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			Bom sbom = SBomFileUtils.processInputStream(stream);
			
			List<Tool> orgTools = sbom.getMetadata().getTools();
			
			String toolName = "testTool";
			String toolVersion = "1.1.2";
			
			String compName = "newComponent";
			String compGroup = "com.lmco.efoss";
			String compVersion = "1.5.1";
			Type compType = Component.Type.CONTAINER;
			
			Component expectedComponent = new Component();
			expectedComponent.setName(compName);
			expectedComponent.setGroup(compGroup);
			expectedComponent.setVersion(compVersion);
			expectedComponent.setType(compType);
			
			ComponentUtilities.addNewOuterComponent(sbom, toolName, toolVersion, compName,
					compGroup, compVersion, null);
			
			Metadata metadata = sbom.getMetadata();
			Component actualComponent = metadata.getComponent();
			
			ComponentComparator comparator = new ComponentComparator();
			
			if (comparator.equals(expectedComponent, actualComponent))
				watcher.getLogger().info("Successfuly created a new Metadata Component!");
			else
				watcher.getLogger().warn("Failed to create the new Metadata Component:" +
						"\n	Expected: " + ComponentUtilities.toString(expectedComponent, "		") +
						"\n	Actual: " + ComponentUtilities.toString(actualComponent, "		"));
			
			Assert.assertTrue(comparator.equals(expectedComponent, actualComponent));
			
			List<Tool> tools = metadata.getTools();
			if ((orgTools.size() + 1) == tools.size())
				watcher.getLogger().info("Got the correct number of Tools (original plus one).");
			else
				watcher.getLogger().warn("Did NOT get the expected number of Tools." +
						"\n	Expected: " + (orgTools.size() + 1) +
						"\n	Got: " + tools.size());
			
			Assert.assertEquals((orgTools.size() + 1), tools.size());
			
			Tool expectedTool = new Tool();
			expectedTool.setName(toolName);
			expectedTool.setVendor("Lockheed Martin");
			expectedTool.setVersion(toolVersion);
			
			Tool actualTool = tools.get(0);
			ToolsCustomComparator toolComparator = new ToolsCustomComparator();
			
			if (toolComparator.equals(expectedTool, actualTool))
				watcher.getLogger().info("Created Expected Tool!");
			else
				watcher.getLogger().warn("Did NOT create the expected tool." +
						"\n	Expected: " + ToolsUtils.toString(expectedTool, "		") + "\n" +
						"\n	Got: " + ToolsUtils.toString(actualTool, "		"));
			
			Assert.assertTrue(toolComparator.equals(expectedTool, actualTool));
		}
		catch (SBomCommonsException sbomE)
		{
			String error = "Unexpected error occurred while attempting to read SWBom from " +
					"input stream!";
			watcher.getLogger().error(error, sbomE);
			Assert.fail(error);
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting create a new Outer " +
					"empty component (with tool).";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This method is used to test the toString method in the componentUtils.
	 */
	@Test
	public void componentToString()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		try
		{
			String compName = "newComponent";
			String compGroup = "com.lmco.efoss";
			String compVersion = "1.5.1";
			Type compType = Component.Type.CONTAINER;
			
			Component comp = new Component();
			comp.setName(compName);
			comp.setGroup(compGroup);
			comp.setVersion(compVersion);
			comp.setType(compType);
			
			String expectedStringWithoutTabs = "Name:newComponent\n" +
					"Group: com.lmco.efoss\n" +
					"Version: 1.5.1\n";
			String actualStringWithoutTabs = ComponentUtilities.toString(comp);
			
			if (expectedStringWithoutTabs.equals(actualStringWithoutTabs))
				watcher.getLogger()
						.info("Got Expected String from ComponentUtilities.toString(comp)!");
			else
			{
				String diff = StringUtils.difference(expectedStringWithoutTabs,
						actualStringWithoutTabs);
				watcher.getLogger().warn("Failed to generate the expected String from " +
						"ComponentUtilities.toString(comp): \n" +
						"Difference: " + diff);
			}
			Assert.assertTrue(expectedStringWithoutTabs.equals(actualStringWithoutTabs));
			
			String expectedStringWithTabs = "	Name:newComponent\n" +
					"	Group: com.lmco.efoss\n" +
					"	Version: 1.5.1\n";
			
			String actualStringWithTabs = ComponentUtilities.toString(comp, "	");
			
			if (actualStringWithTabs.equals(expectedStringWithTabs))
				watcher.getLogger().info("Got Expected String from " +
						"ComponentUtilities.toString(comp, \"	\")!");
			else
			{
				String diff = StringUtils.difference(expectedStringWithTabs,
						actualStringWithTabs);
				watcher.getLogger().warn("Failed to generate the expected String from " +
						"ComponentUtilities.toString(comp, \"	\"): \n" +
						"Difference: " + diff);
			}
			Assert.assertTrue(expectedStringWithTabs.equals(actualStringWithTabs));
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting ComponentUtilities.toString.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
