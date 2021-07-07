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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

/**
 * (U) This class is used to write the output to a File.
 * 
 * @author wrgoff
 * @since 24 February 2021
 */
public class FileOutputUtils
{
	private static final Logger logger = Logger.getLogger(FileOutputUtils.class.getName());
	
	/**
	 * (U) This method is used to generate the output file.
	 * 
	 * @param output         String value of the output.
	 * @param outputFormat   String value that tells us what format the data in the file will be.
	 *                       Either JSon or XML.
	 * @param outputFileName String value of the file to put the data in.
	 * @throws SBomCommonsException in the event we are unable to produce the desired format, and
	 *                              write it to the file.
	 */
	public static void generateOutputFile(String output, String outputFormat,
			String outputFileName) throws SBomCommonsException
	{
		String outputFormatString = outputFormat.toLowerCase();
		
		try
		{
			if ((outputFileName.endsWith("/")) || (outputFileName.endsWith("\\")))
				Files.createDirectories(Paths.get(outputFileName));
		}
		catch (IOException ioe)
		{
			String error = "Failed to create output directory (" + outputFileName + ").";
			logger.error(error, ioe);
			throw new SBomCommonsException(error, ioe);
		}
		if ((!StringUtils.isValid(outputFileName)) || (outputFileName.endsWith("/")) ||
				(outputFileName.endsWith("\\")))
		{
			outputFileName = outputFileName + "bom";
		}
		
		// Append the file suffix. If it is NOT already there.
		if (!outputFileName.endsWith(outputFormatString))
			outputFileName = outputFileName + "." + outputFormatString;
		
		try
		{
			Files.writeString(Paths.get(outputFileName), output);
		}
		catch (IOException e)
		{
			String error = "Failed to write output to file (" + outputFileName + ").";
			logger.error(error, e);
			throw new SBomCommonsException(error, e);
		}
	}
}
