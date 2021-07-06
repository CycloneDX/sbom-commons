/*
 * Copyright (c) 2018,2019 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package com.lmco.efoss.sbom.commons.utils;

import java.util.Date;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import com.lmco.efoss.sbom.commons.test.utils.TestUtils;

/**
 * (U) This class contains the unit tests for the String Utils class.
 * 
 * @author wrgoff
 * @since 9 July 2020
 */
public class StringUtilsTest
{
	public StringUtils stringUtils = new StringUtils();
	
	private static final String LOG4J_FILE = "StringUtilsAppender.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE,
			"StringUtilsTest");
	
	/**
	 * (U) This test case is used to check an empty string. Making sure it returns Not valid. Or
	 * false from the "StringUtils.isValid" method.
	 */
	@Test
	public void testIsValidEmptyString()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String myTestString = "";
		try
		{
			if (StringUtils.isValid(myTestString))
				Assert.fail("Empty String should NOT be valid!");
			else
				watcher.getLogger().debug("Empty String is NOT Valid, as expected!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"the validity of an empty String!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case is used to check a null string. Making sure it returns Not valid. Or false
	 * from the "StringUtils.isValid" method.
	 */
	@Test
	public void testIsValidNullString()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String myTestString = null;
		try
		{
			if (StringUtils.isValid(myTestString))
				Assert.fail("Null String should NOT be valid!");
			else
				watcher.getLogger().debug("Null String is NOT Valid, as expected!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"the validity of a Null String!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case is used to check a valid string. Making sure it returns valid. Or true
	 * from the "StringUtils.isValid" method.
	 */
	@Test
	public void testIsValidRealString()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String myTestString = "Some String Value";
		try
		{
			if (StringUtils.isValid(myTestString))
			{
				watcher.getLogger().debug("String(" + myTestString +
						") is valid, as expected!");
			}
			else
				Assert.fail("String(" + myTestString + ") should be valid!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"the validity of a valid String(" + myTestString + ")!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case is used to check if two strings match.
	 */
	public @Test
			void testStringsMatch()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String str1 = "Some String Value";
		String str2 = "some string value";
		try
		{
			if (StringUtils.equals(str1, str2))
			{
				watcher.getLogger().debug("Strings str1(" + str1 +
						") and str2 (" + str2 + ") match (ignore case), as expected!");
			}
			else
				Assert.fail("Strings str1(" + str1 + ") and str2 (" + str2 +
						") should match (ignore case)!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"if two strings match";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case is used to check if two strings match.
	 */
	public @Test
			void testNullStringsMatch()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String str1 = null;
		String str2 = null;
		try
		{
			if (StringUtils.equals(str1, str2))
			{
				watcher.getLogger().debug("Strings str1(" + str1 +
						") and str2 (" + str2 + ") match (ignore case), " +
						"as expected!");
			}
			else
				Assert.fail("Strings str1(" + str1 + ") and str2 (" + str2 +
						") should match (ignore case)!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"if two strings match";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case is used to test the removal of white space from a String.
	 */
	@Test
	public void testRemovalOfSpacesFromString()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String startingString = "some string	value\n";
		try
		{
			String expectedString = "somestringvalue";
			
			String actualString = StringUtils.removeSpaces(startingString);
			if (expectedString.equals(actualString))
				watcher.getLogger().debug("Removal of white space from String worked as expected!");
			else
				watcher.getLogger().warn("Removal of white space from String did NOT work as " +
						"expected.\n	Expected: " + expectedString +
						"\n	Actual: " + actualString);
			
			Assert.assertEquals(expectedString, actualString);
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to remove white space from " +
					"String!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case is used to check if two strings match.
	 */
	@Test
	public void testStringsDoNotMatch()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String str1 = "Some test case";
		String str2 = null;
		try
		{
			if (StringUtils.equals(str1, str2))
			{
				Assert.fail("Strings str1(" + str1 + ") and str2 (" + str2 +
						") should NOT match (ignore case)!");
			}
			else
				watcher.getLogger().debug("Strings str1(" + str1 +
						") and str2 (" + str2 +
						") do NOT match (ignore case), as expected!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"if two strings match";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case is used to check if two strings match.
	 */
	@Test
	public void testStringsDoNotMatch2()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String str1 = "Some test case";
		String str2 = "Someother String";
		try
		{
			if (StringUtils.equals(str1, str2))
			{
				Assert.fail("Strings str1(" + str1 + ") and str2 (" + str2 +
						") should NOT match (ignore case)!");
			}
			else
				watcher.getLogger().debug("Strings str1(" + str1 +
						") and str2 (" + str2 +
						") do NOT match (ignore case), as expected!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"if two strings match";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
