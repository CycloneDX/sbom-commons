/*
 * Copyright (c) 2018,2019 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package com.lmco.efoss.sbom.commons.test.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.cyclonedx.model.Bom;

import com.lmco.efoss.sbom.commons.utils.DateUtils;
import com.lmco.efoss.sbom.commons.utils.SBomFileUtils;

/**
 * (U) This class contains utilities used by the various test classes.
 * 
 * @author wrgoff
 * @since 9 July 2020
 */
public class TestUtils
{
	/**
	 * (U) This method is used to log the starting of a test.
	 * 
	 * @param methodName String value of the method that test was started for.
	 * @param logger     Logger to log the event to.
	 * @return Date the java.util.Date indicating when the test started.
	 */
	public static Date logTestStart(String methodName, Logger logger)
	{
		Date startDate = DateUtils.rightNowDate();
		
		String msg = "Starting " + methodName + " Test (" +
				DateUtils.dateAsPrettyString(startDate) + ").";
		
		if (logger.isInfoEnabled())
			logger.info(msg);
		
		System.out.println(msg);
		
		return startDate;
	}
	
	/**
	 * (U) This method is used to log the finishing of a test.
	 * 
	 * @param methodName String value of the method the test was for.
	 * @param startDate  Date the test started.
	 * @param logger     Logger to log the event to.
	 */
	public static void logTestFinish(String methodName, Date startDate, Logger logger)
	{
		if (logger.isInfoEnabled())
			logger.info("It took " + DateUtils.computeDiff(startDate, DateUtils.rightNowDate()) +
					" to run the " + methodName + " Test!\n\n");
	}
	
	/**
	 * (U) This method is used to read a Software Bill Of Materials (SBOM) in.
	 * 
	 * @param fileName String value of the name of the file in the resources 
	 * directory to read.
	 * @param logger Log4j Logger to print an errors to.
	 * @return Bom CycloneDx Bom created from the file passed in.
	 */
	public Bom readSbomFile(String fileName, Logger logger)
	{
		Bom sbom = null;
				
		try (InputStream stream = getClass().getResourceAsStream("/" + fileName))
		{
			sbom = SBomFileUtils.processInputStream(stream);
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"process bom file (" + fileName + ").";
		logger.error(error, e);
		}
		return sbom;		
	}
	
	/**
	 * (U) This method is used to read a Software Bill Of Materials (SBOM) in.
	 * 
	 * @param fileName String value of the name of the file in the resources 
	 * directory to read.
	 * @param logger Log4j Logger to print an errors to.
	 * @return Bom CycloneDx Bom created from the file passed in.
	 */
	public Bom readSbomFileFromAbsolutePath(String fileName, Logger logger)
	{
		Bom sbom = null;
				
		try
		{
			File file = new File(fileName);
			if ((file.exists()) && (file.canRead()))
				sbom = SBomFileUtils.processFile(file);
		}
		catch (Exception e)
		{
			String error = "Unexpected error occurred while attempting to " +
					"process bom file (" + fileName + ").";
		logger.error(error, e);
		}	
		return sbom;		
	}
}
