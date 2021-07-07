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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.exceptions.JSonParseException;

/**
 * (U) This utility class is used for the manipulation of JSon.
 * @author wrgoff
 * @since 29 April 2021
 */
public class JSonUtils
{
	/**
	 * (U) Private Constructor.
	 */
	private JSonUtils()
	{
	}
	
	/**
	 * (U) This method is used to parse a JSon String into a Class.
	 * 
	 * @param <T>     Class reference to attempt to parse the JSon String into.
	 * @param json    String that we are attempting to parse.
	 * @param myClass The Class to parse the JSon into.
	 * @return T instance of the created from the JSon String passed in..
	 * @throws JSonParseException in the event we are unable to parse the JSon String into the
	 *                            class.
	 */
	public static <T> T parseJson(String json, Class<T> myClass)
			throws JSonParseException
	{
		T obj = null;
		ObjectMapper objMapper = new ObjectMapper();
		
		try
		{
			obj = objMapper.readValue(json, myClass);
		}
		catch (Exception e)
		{
			throw new JSonParseException("Failed to parse JSon into Object (" + myClass.getName() + ")!", e);
		}
		return obj;
	}
}
