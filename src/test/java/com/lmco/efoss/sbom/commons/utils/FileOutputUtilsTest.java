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

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.cyclonedx.model.Bom;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import com.lmco.efoss.sbom.commons.test.utils.Log4JTestWatcher;
import com.lmco.efoss.sbom.commons.test.utils.TestUtils;
import com.lmco.efoss.sbom.commons.utils.SBomCommons.AVAILABLE_FORMATS;

/**
 * (U) This class is used to test the File Output Utilities. Or to put it simply the writing of an
 * SBom to an output file.
 * 
 * @author wrgoff
 * @since 01 March 2021
 */
public class FileOutputUtilsTest
{
	public FileOutputUtils foUtils = new FileOutputUtils();
	
	private static final String LOG4J_FILE = "FileOutputUtilsAppender.xml";
	
	private static final String fileName = "OrgSbom.xml";
	
	@ClassRule
	public static Log4JTestWatcher watcher = new Log4JTestWatcher(LOG4J_FILE,
			"FileOutputUtilsTest");
	
	/**
	 * (U) This test method is used to test the writing of a JSon SBom to a file.
	 */
	@Test
	public void produceJSonSBomFile()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		AVAILABLE_FORMATS format = AVAILABLE_FORMATS.JSON;
		
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			String output = null;
			try
			{
				Bom sbom = SBomFileUtils.processInputStream(stream);
				output = SBomCommons.generateOutputString(sbom, format);
			}
			catch (SBomCommonsException e)
			{
				String error = "Unexpected error occurred while attempting to " +
						"process bom file (" + fileName + ") into a String.";
				watcher.getLogger().error(error, e);
				Assert.fail(error);
			}
			
			FileOutputUtils.generateOutputFile(output, format.toString(),
					"./output/");
			
			File bomFile = new File("./output/bom." + format.toString().toLowerCase());
			if (bomFile.exists())
				watcher.getLogger().debug("File (./output/bom." + format.toString().toLowerCase() +
						") exists, as expected!");
			else
			{
				String error = "File (./output/bom." + format.toString().toLowerCase() +
						") does NOT exists!";
				watcher.getLogger().error(error);
				Assert.fail(error);
			}
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"generate bom output file.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			try
			{
				Files.deleteIfExists(Paths.get("./output/bom." + format.toString().toLowerCase()));
				Files.deleteIfExists(Paths.get("./output"));
			}
			catch (Exception e)
			{
				watcher.getLogger().warn("Unable to cleanup output directory!", e);
			}
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
	
	/**
	 * (U) This test method is used to test the writing of a XML SBom to a file.
	 */
	@Test
	public void produceXmlSBomFile()
	{
		String methodName = new Object()
		{}.getClass().getEnclosingMethod().getName();
		Date startDate = DateUtils.rightNowDate();
		
		TestUtils.logTestStart(methodName, watcher.getLogger());
		
		AVAILABLE_FORMATS format = AVAILABLE_FORMATS.XML;
		
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			String output = null;
			try
			{
				Bom sbom = SBomFileUtils.processInputStream(stream);
				output = SBomCommons.generateOutputString(sbom, format);
			}
			catch (SBomCommonsException e)
			{
				String error = "Unexpected error occurred while attempting to " +
						"process bom file (" + fileName + ") into a String.";
				watcher.getLogger().error(error, e);
				Assert.fail(error);
			}
			
			FileOutputUtils.generateOutputFile(output, format.toString(),
					"./output/");
			
			File bomFile = new File("./output/bom." + format.toString().toLowerCase());
			if (bomFile.exists())
				watcher.getLogger().debug("File (./output/bom." + format.toString().toLowerCase() +
						") exists, as expected!");
			else
			{
				String error = "File (./output/bom." + format.toString().toLowerCase() +
						") does NOT exists!";
				watcher.getLogger().error(error);
				Assert.fail(error);
			}
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"generate bom output file.";
			watcher.getLogger().error(error, e);
			Assert.fail(error);
		}
		finally
		{
			try
			{
				Files.deleteIfExists(Paths.get("./output/bom." + format.toString().toLowerCase()));
				Files.deleteIfExists(Paths.get("./output"));
			}
			catch (Exception e)
			{
				watcher.getLogger().warn("Unable to cleanup output directory!", e);
			}
			TestUtils.logTestFinish(methodName, startDate, watcher.getLogger());
		}
	}
}
