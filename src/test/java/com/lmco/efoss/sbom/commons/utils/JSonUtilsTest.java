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

import java.util.Date;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import com.lmco.efoss.sbom.commons.dtos.TestObj;
import com.lmco.efoss.sbom.commons.exceptions.JSonParseException;
import com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import com.lmco.efoss.sbom.commons.test.utils.TestUtils;


/**
 * (U) Test methods for the Jackson JSonUtils class.
 * 
 * @author wrgoff
 * @since 03 May 2021
 */
public class JSonUtilsTest
{
	private static final String LOG4J_FILE = "JSonUtilsLog4J.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE, "JSonUtilsTest");
	
	/**
	 * (U) This method tests our JSon Parsing of a TestObj.
	 */
	@Test
	public void GoodJsontest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
			
		Date startDate = DateUtils.rightNowDate();
			
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		TestObj expectedTestObj = new TestObj();
		expectedTestObj.setDir(".terraform/modules/common-deny-arn");
		expectedTestObj.setKey("common-deny-arn");
		expectedTestObj.setSource("terraform-dev.us.lmco.com/site-admins/common-deny-arn/aws");
		expectedTestObj.setVersion("1.0.1");
		
		String jsonString = "{\"Key\":\"common-deny-arn\",\"Source\":\"terraform-dev.us.lmco.com/site-admins/common-deny-arn/aws\",\"Version\":\"1.0.1\",\"Dir\":\".terraform/modules/common-deny-arn\"}";
		try
		{
			TestObj actualTestObj = JSonUtils.parseJson(jsonString, TestObj.class);
			
			if (expectedTestObj.equals(actualTestObj))
				watcher.getLogger().debug("Parsed json into expected TestObj.");
			else
				watcher.getLogger().warn("Did not parse JSon into expected Module!" +
						"\n	Expected: \n" + expectedTestObj.toString("		") +
						"\n	Actual: \n" + actualTestObj.toString("		"));
			Assert.assertTrue(expectedTestObj.equals(actualTestObj));
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to compare " +
					"two TestObjs.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This method tests our Exception handling for JSonUtils.
	 */
	@Test
	public void BadJsontest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
			
		Date startDate = DateUtils.rightNowDate();
			
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		String jsonString = "{\"Key1\":\"common-deny-arn\",\"Source1\":\"terraform-dev.us.lmco.com/site-admins/common-deny-arn/aws\",\"Version1\":\"1.0.1\",\"Dir1\":\".terraform/modules/common-deny-arn\"}";
		try
		{	
			Exception exception = Assert.assertThrows(JSonParseException.class, () ->
			{
				JSonUtils.parseJson(jsonString, TestObj.class);
			});
			
			String expectedMessage = "Failed to parse JSon into Object";
			String actualMessage = exception.getMessage();
			
			if(actualMessage.contains(expectedMessage))
				watcher.getLogger().debug("Actual Message contains expected message: " + actualMessage);
			else
				watcher.getLogger().warn("Actual Message does NOT contain expected message (" + expectedMessage + 
						").\n	Actual Message: " + actualMessage);
			Assert.assertTrue(actualMessage.contains(expectedMessage));
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to test our exception being thrown " +
					"from the JSonUtils.parse method.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
