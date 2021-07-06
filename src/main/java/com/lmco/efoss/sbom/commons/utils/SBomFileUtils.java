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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.cyclonedx.exception.ParseException;
import org.cyclonedx.model.Bom;
import org.cyclonedx.parsers.JsonParser;
import org.cyclonedx.parsers.Parser;
import org.cyclonedx.parsers.XmlParser;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * (U) This utility class is used to manipulate Software Bill of Materials (SBOM) files.
 * 
 * @author wrgoff
 * @since 14 May 2020
 */
public class SBomFileUtils
{
	private static final Logger logger = Logger.getLogger(SBomFileUtils.class.getName());
	private static final String GENERIC_ERROR = "Unexpected error occurred while attempting to " +
			"process bom file.";
	
	public enum VALID_FILE_CONTENTS
	{
		JSON, XML
	}
	
	/**
	 * (U) This method is used to parse the File into a Software Bill of Materials (SBOM).
	 * 
	 * @param file File to parse.
	 * @return Bom parsed from the File passed in.
	 * @throws SBomCommonsException in the event we are unable to parse the File passed in.
	 */
	public static Bom processFile(File file) throws SBomCommonsException
	{
		Bom sbom = null;
		
		try (InputStream stream = new FileInputStream(file))
		{
			sbom = processInputStream(stream);
		}
		catch (SBomCommonsException me)
		{
			throw me;
		}
		catch (IOException ioe)
		{
			String error = "Failed to read file passed in.";
			logger.error(error);
			throw new SBomCommonsException(error);
		}
		catch (Exception e)
		{
			logger.error(GENERIC_ERROR, e);
			throw new SBomCommonsException(GENERIC_ERROR);
		}
		return sbom;
	}
	
	/**
	 * (U) This method is used to parse the InputStream into a Software Bill of Materials (SBOM).
	 * 
	 * @param stream InputStream to parse.
	 * @return Bom parsed from the Input Stream passed in.
	 * @throws SBomCommonsException in the event we are unable to parse the InputStream passed in.
	 */
	public static Bom processInputStream(InputStream stream) throws SBomCommonsException
	{
		Bom sbom = null;
		
		try
		{
			String text = IOUtils.toString(stream, StandardCharsets.UTF_8.name());
			
			sbom = processString(text);
		}
		catch (SBomCommonsException sce)
		{
			throw sce;
		}
		catch (IOException ioe)
		{
			String error = "Failed to read file passed in.";
			logger.error(error);
			throw new SBomCommonsException(error);
		}
		catch (Exception e)
		{
			logger.error(GENERIC_ERROR, e);
			throw new SBomCommonsException(GENERIC_ERROR);
		}
		return sbom;
	}
	
	/**
	 * (U) This method is used to parse the String passed in, into a Software Bill of Materials
	 * (SBOM).
	 * 
	 * @param text String to parse into a Bom.
	 * @return Bom parsed from the String passed in.
	 * @throws SBomCommonsException in the event we are unable to parse the String passed in.
	 */
	public static Bom processString(String text) throws SBomCommonsException
	{
		Bom sbom = null;
		
		try
		{
			VALID_FILE_CONTENTS fileType = getStringType(text);
			
			Parser parser = null;
			
			if (fileType.equals(VALID_FILE_CONTENTS.JSON))
			{
				parser = new JsonParser();
				sbom = parser.parse(text.getBytes());
			}
			else
			{
				parser = new XmlParser();
				sbom = parser.parse(text.getBytes());
			}
			
		}
		catch (SBomCommonsException sce)
		{
			throw sce;
		}
		catch (ParseException pe)
		{
			String error = "Failed to parse the bom.xml.";
			logger.error(error, pe);
			throw new SBomCommonsException(error);
		}
		catch (Exception e)
		{
			logger.error(GENERIC_ERROR, e);
			throw new SBomCommonsException(GENERIC_ERROR);
		}
		return sbom;
	}
	
	/**
	 * (U) This method is used to get the type of contents the file contains. Either JSon or XML.
	 * 
	 * @param text String value to get the contents for.
	 * @return VALID_FILE_CONTENTS enumeration that tell us if the string contains either JSon or
	 *         XML.
	 * @throws SBomCommonsException if the contains are NOT either valid JSon or XML.
	 */
	public static VALID_FILE_CONTENTS getStringType(String text) throws SBomCommonsException
	{
		try
		{
			new ObjectMapper().readTree(text);
			logger.info("File contents appear to be valid JSON.");
			return VALID_FILE_CONTENTS.JSON;
		}
		catch (IOException e)
		{
			logger.warn("File contents are not valid JSON, trying Xml.");
		}
		
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			
			// Disable XML External Entities attacks.
			docFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			docFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			docFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			docFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			
			docFactory.newDocumentBuilder().parse(new InputSource(new StringReader(text)));
			
			logger.info("File contents appear to ve valid XML.");
			return VALID_FILE_CONTENTS.XML;
		}
		catch (Exception e)
		{
			String error = "File contents are NOT either valid JSon, or valid XML!";
			logger.error(error);
			throw new SBomCommonsException(error);
		}
	}
}
