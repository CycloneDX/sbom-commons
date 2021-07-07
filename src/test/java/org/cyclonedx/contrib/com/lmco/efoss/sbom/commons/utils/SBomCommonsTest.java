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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;
import org.cyclonedx.model.ExternalReference;
import org.cyclonedx.model.ExternalReference.Type;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.test.utils.TestUtils;

public class SBomCommonsTest
{
	private SBomCommons sbomCommons = new SBomCommons();
	
	private static final String LOG4J_FILE = "SBomCommonsAppender.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE,
			"SBomCommonsTest");
	
	/**
	 * (U) This test method is used to test the creation of a Bom from an XML File.
	 */
//	@Test
	public void generateXMLOutputStringTest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		String bomFileName = "ubuntuBom.xml";
		TestUtils.logTestStart(methodName, watcher.getLogger());
		try (InputStream stream = getClass().getResourceAsStream("/" + bomFileName))
		{
			Bom testBom = null;
			try
			{
				testBom = SBomFileUtils.processInputStream(stream);
			}
			catch (Exception e)
			{
				String error = "Unexpected error occurred while attempting to " +
						"process bom file (" + bomFileName + ") into a String.";
				watcher.getLogger().error(error, e);
				Assert.fail(error);
			}
			String xmlString = SBomCommons.generateOutputString(testBom,
					SBomCommons.AVAILABLE_FORMATS.XML);
			
			Bom bomFromXmlString = SBomFileUtils.processString(xmlString);
			
			Assert.assertEquals(testBom.getComponents().size(),
					bomFromXmlString.getComponents().size());
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"process XML bom String.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case makes sure a set Reference type is not reset.
	 */
	@Test
	public void unChangedReferenceTypetest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		Component component = new Component();
		component.setName("log4j");
		component.setGroup("log4j");
		component.setVersion("1.12.1");
		
		List<ExternalReference> refs = new ArrayList<ExternalReference>();
		ExternalReference ref = new ExternalReference();
		ref.setType(Type.WEBSITE);
		refs.add(ref);
		component.setExternalReferences(refs);
		
		try
		{
			SBomCommons.checkReferenceTypes(component);
			
			List<ExternalReference> myRefs = component.getExternalReferences();
			
			for (ExternalReference myRef : myRefs)
			{
				if (myRef.getType().equals(Type.OTHER))
					Assert.fail("The Reference Type should NOT have been reset!");
			}
			watcher.getLogger().debug("Reference type is correct!");
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"the setting of null reference types.!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test case makes sure an null Reference type is set.
	 */
	@Test
	public void changedReferenceTypetest()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		Component component = new Component();
		component.setName("log4j");
		component.setGroup("log4j");
		component.setVersion("1.12.1");
		
		List<ExternalReference> refs = new ArrayList<ExternalReference>();
		ExternalReference ref = new ExternalReference();
		refs.add(ref);
		component.setExternalReferences(refs);
		
		try
		{
			SBomCommons.checkReferenceTypes(component);
			
			List<ExternalReference> myRefs = component.getExternalReferences();
			
			for (ExternalReference myRef : myRefs)
			{
				if (myRef.getType().equals(Type.OTHER))
					watcher.getLogger().debug("Reference type is correct!");
				else
					Assert.fail("The Reference Type should NOT have been reset!");
			}
		}
		catch (Exception e)
		{
			String error = "Unexpected error occured while attempt to check " +
					"the setting of null reference types.!";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
