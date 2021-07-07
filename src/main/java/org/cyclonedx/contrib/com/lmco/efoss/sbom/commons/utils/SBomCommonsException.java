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
 * (U) This exception class is used by the SBom Common Utilities as their base exception class.
 * 
 * @author wrgoff
 * @since 8 Aug 2020
 */
public class SBomCommonsException extends Exception
{
	private static final long serialVersionUID = -1514830729064266772L;
	
	/**
	 * (U) Constructs a new SBomCommonsException with null as its details.
	 */
	public SBomCommonsException()
	{}
	
	/**
	 * (U) Constructs a new SBomCommonsException with the specified detail message.
	 *
	 * @param message String value to set the message to.
	 */
	public SBomCommonsException(String message)
	{
		super(message);
	}
	
	/**
	 * (U) Constructs a new SBomCommonsException with the specified detail message and cause.
	 *
	 * @param message String value to set the message to.
	 * @param cause   Throwable class to set the cause to.
	 */
	public SBomCommonsException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	/**
	 * (U) Constructs a new SBomCommonsException wit the specified detail message, cause,
	 * suppression flag set to either enabled or disabled, and the writable stack trace flag set to
	 * either enable or disabled.
	 *
	 * @param message             String value to set the message to.
	 * @param cause               Throwable class to set the cause to.
	 * @param enableSuppression   boolean used to set the enabled suppression flag to.
	 * @param writeableStackTrace boolean used to set the write able stack trace flag to.
	 */
	public SBomCommonsException(String message, Throwable cause, boolean enableSuppression,
			boolean writeableStackTrace)
	{
		super(message, cause, enableSuppression, writeableStackTrace);
	}
	
	/**
	 * (U) Constructs a new SBomCommonsException with the cause set.
	 * 
	 * @param cause Throwable class to set the cause to.
	 */
	public SBomCommonsException(Throwable cause)
	{
		super(cause);
	}
}