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

import org.apache.log4j.Logger;
import org.cyclonedx.model.Component.Type;

/**
 * (U) This utility class is used to lookup a Cyclone-Dx Type.
 * 
 * @author wrgoff
 * @since 13 April 2021
 */
public class TypeUtils
{
	/**
	 * (U) Constructor.
	 */
	private TypeUtils()
	{}
	
	private static final Logger logger = Logger.getLogger(TypeUtils.class.getName());
	
	/**
	 * (U) This method is used to build and validate the type.
	 * 
	 * @param type String value of the type the user supplied.
	 * @return Type enumeration that the component will be set to.
	 */
	public static Type lookupType(String type)
	{
		
		if (type.trim().equalsIgnoreCase(Type.APPLICATION.toString().trim()))
			return Type.APPLICATION;
		else if (type.trim().equalsIgnoreCase(Type.CONTAINER.toString().trim()))
			return Type.CONTAINER;
		else if (type.trim().equalsIgnoreCase(Type.DEVICE.toString().trim()))
			return Type.DEVICE;
		else if (type.trim().equalsIgnoreCase(Type.FILE.toString().trim()))
			return Type.FILE;
		else if (type.trim().equalsIgnoreCase(Type.FIRMWARE.toString().trim()))
			return Type.FIRMWARE;
		else if (type.trim().equalsIgnoreCase(Type.FRAMEWORK.toString().trim()))
			return Type.FRAMEWORK;
		else if (type.trim().equalsIgnoreCase(Type.LIBRARY.toString().trim()))
			return Type.LIBRARY;
		else if (type.trim().equalsIgnoreCase(Type.OPERATING_SYSTEM.toString().trim()))
			return Type.OPERATING_SYSTEM;
		else
		{
			logger.warn("Unable to determine type(" + type + "), setting to Container");
			return Type.CONTAINER;
		}
	}
}
