/*
 * Copyright (c) 2018,2019, 2020, 2021 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.dtos;

/**
 * (U) Base Data Transfer Object, contains methods used by other DTOs.
 * 
 * @author wrgoff
 * @since 03 May 2021
 */
public class BaseDto
{
	/**
	 * (U) Convenience method used to compare Strings.  
	 * @param val1 String to compare to the next String.
	 * @param val2 String to compare to the first one.
	 * @return int the results of the compare.
	 */
	protected int compareStr(String val1, String val2)
	{
		int comp = -1;
		if((val1 == null) && (val2 == null))
			comp = 0;
		else if(val1 == null)
			comp = -1;
		else if(val2 == null)
			comp = 1;
		else
			comp = val1.compareTo(val2);
		return comp;
	}
	
	/**
	 * (U) This convenience method is used to get an empty String in the event of a null. It is used
	 * to compare this Data Set Object to another object.
	 * 
	 * @param str String to get the String value for.
	 * @return String either the string passed in or an empty string if the String passed in was
	 *         null.
	 */
	protected static String getStringValue(String str)
	{
		String value = "";
		
		if (str != null)
			value = str;
		return value;
	}
	
}
