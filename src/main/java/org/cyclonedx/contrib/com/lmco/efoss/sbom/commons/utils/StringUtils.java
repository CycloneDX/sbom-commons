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

/**
 * (U) This class contains String Utilities.
 * 
 * @author wrgoff
 * @since 21 May 2020
 */
public class StringUtils
{
	/**
	 * (U) This utility method was used to test strings, to see if we have a valid string. That It
	 * is NOT null and has an actual length large than 0.
	 * 
	 * @param str String to test.
	 * @return boolean true if the string passed in is not null and has an actual length (after
	 *         trimming white space) larger than 0.
	 */
	public static boolean isValid(String str)
	{
		boolean valid = false;
		
		if ((str != null) && (str.trim().length() > 0))
			valid = true;
		return valid;
	}
	
	/**
	 * (U) This method is used to see if two Strings are the same. Either both Null, or contain the
	 * same string (regardless of case).
	 * 
	 * @param str1 String to use for the comparison.
	 * @param str2 String to use for the comparison.
	 * @return boolean true if both strings are the same, regardless of case. Or if both strings are
	 *         null. False otherwise.
	 */
	public static boolean equals(String str1, String str2)
	{
		boolean same = false;
		
		if ((str1 == null) && (str2 == null))
			same = true;
		else if ((str1 == null) || (str2 == null))
			same = false;
		else if (str1.equalsIgnoreCase(str2))
			same = true;
		else
			same = false;
		return same;
	}
	
	/**
	 * (U) This convenience method is used to remove all spaces from the string passed in.
	 * 
	 * @param str String to remove the spaces from.
	 * @return String the string without spaces.
	 */
	public static String removeSpaces(String str)
	{
		if (str != null)
			return (str.replaceAll("\\s", ""));
		else
			return "";
	}
}
