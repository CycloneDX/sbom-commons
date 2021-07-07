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

import java.util.List;

import org.apache.log4j.Logger;
import org.cyclonedx.BomGeneratorFactory;
import org.cyclonedx.CycloneDxSchema.Version;
import org.cyclonedx.generators.json.BomJsonGenerator;
import org.cyclonedx.generators.xml.BomXmlGenerator;
import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;
import org.cyclonedx.model.ExternalReference;

/**
 * (U) This class contains Software Bill of Materials commonly used methods.
 * 
 * @author wrgoff
 * @since 9 Sept 2020
 */
public class SBomCommons
{
	private static final Logger logger = Logger.getLogger(SBomCommons.class.getName());

	public enum AVAILABLE_FORMATS
	{
		JSON, XML
	}
	
	/**
	 * (U) Because of a bug in CycloneDx's creation of a JSon SBom, I had to add this code to make
	 * Sure all External References have a type. Their code throws a Null Pointer.
	 * 
	 * @param component Component to check (and fill in if necessary) any External References
	 *                  without a type set.
	 */
	public static void checkReferenceTypes(Component component)
	{
		List<ExternalReference> refs = component.getExternalReferences();
		if (refs != null)
		{
			for (ExternalReference ref : refs)
			{
				if (ref.getType() == null)
					ref.setType(ExternalReference.Type.OTHER);
			}
		}
	}
	
	/**
	 * (U) This method is used to generate the output from the Software Bill of Materials.
	 * NOTE:  Because of an issue with CycloneDx's generation of XML and JSon, specifically
	 * with ExtensibleTypes, not being handled in a way that they can be read in.
	 * 
	 * @param bom    Bom to generate the output from.
	 * @param format Enumeration that tells us what format is wanted.
	 * @return String the output of the Bill of Materials.
	 * @throws SBomCommonsException in the event we can not produce a string from the BOM.
	 */
	public static String generateOutputString(Bom bom, AVAILABLE_FORMATS format)
			throws SBomCommonsException
	{
		String output;
		
		try
		{
			if (format.equals(AVAILABLE_FORMATS.JSON))
			{
				BomJsonGenerator generator = BomGeneratorFactory.createJson(
						Version.VERSION_13, bom);
//				generator.generate();
				output = generator.toJsonString();
			}
			else
			{
				BomXmlGenerator generator = BomGeneratorFactory.createXml(
						Version.VERSION_13, bom);
				generator.generate();
				output = generator.toXmlString();
			}
		}
		catch (Exception e)
		{
			String error = "Unable to generate " + format.toString() + " from SBom!";
			logger.error(error, e);
			throw new SBomCommonsException(error);
		}
		return output;
	}
}
