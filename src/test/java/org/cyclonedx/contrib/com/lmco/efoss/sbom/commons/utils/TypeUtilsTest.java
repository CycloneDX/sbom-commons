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

import java.util.Date;

import org.cyclonedx.model.Component.Type;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.TestUtils;

/**
 * @author wrgoff
 *
 * @since 13 April 2021
 */
public class TypeUtilsTest
{
	private static final String LOG4J_FILE = "TypeUtilsAppender.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE, "TypeUtilsTest");
	
	/**
	 * (U) Test method to lookup "application" type..
	 */
	@Test
	public void applicationTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());

		String expectedTypeString = Type.APPLICATION.toString();

		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "CONTAINER" type..
	 */
	@Test
	public void containerTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.CONTAINER.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " + 
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " + 
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "Device" type..
	 */
	@Test
	public void deviceTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.DEVICE.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "File" type..
	 */
	@Test
	public void fileTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.FILE.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "FIRMWARE" type..
	 */
	@Test
	public void firmwareTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.FIRMWARE.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "FRAMEWORK" type..
	 */
	@Test
	public void frameworkTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.FRAMEWORK.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "LIBRARY" type..
	 */
	@Test
	public void libraryTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.LIBRARY.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "OPERATING_SYSTEM" type..
	 */
	@Test
	public void osTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.OPERATING_SYSTEM.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType(expectedTypeString);
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) Test method to lookup "OPERATING_SYSTEM" type..
	 */
	@Test
	public void defaultTypeLookuptest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String expectedTypeString = Type.CONTAINER.toString();
		
		try
		{
			Type actualType = TypeUtils.lookupType("bogus");
			
			if (expectedTypeString.equalsIgnoreCase(actualType.toString()))
				watcher.getLogger().info("Successfuly looked up " + expectedTypeString + " type.");
			else
			{
				StringBuilder sb = new StringBuilder("Did NOT get the expected results:\n");
				sb.append("	Type: expected " + expectedTypeString + ",	got " +
						actualType.toString());
				watcher.getLogger().warn(sb.toString());
			}
			Assert.assertEquals("Type", expectedTypeString, actualType.toString());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempting to lookup " +
					expectedTypeString + " type!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
