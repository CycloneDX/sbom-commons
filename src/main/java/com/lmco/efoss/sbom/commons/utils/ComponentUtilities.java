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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.cyclonedx.model.Bom;
import org.cyclonedx.model.Component;
import org.cyclonedx.model.Component.Type;
import org.cyclonedx.model.Metadata;
import org.cyclonedx.model.Tool;

/**
 * @author wrgoff
 *
 * @since 13 April 2021
 */
public class ComponentUtilities
{
	private static final Logger logger = Logger
			.getLogger(ComponentUtilities.class.getName());
	
	/**
	 * (U) Constructor.
	 */
	private ComponentUtilities()
	{}
	
	/**
	 * (U) This method build a new outer component, and adds a tool matching the name passed in, and
	 * attempts to lookup the Tools version (based on the jar's sub name) of the tool.
	 * 
	 * @param bom         SWBom to add the metadata component to.
	 * @param toolName    String value to assign to the tool's name.
	 * @param toolVersion String value to assign the tool's version.
	 */
	public static void addNewOuterComponent(Bom bom, String toolName, String toolVersion)
	{
		addNewOuterComponent(bom, toolName, toolVersion, null, null, null, null);
	}
	
	/**
	 * (U) This method is used to add the Metadata component for the Software Bill of Materials
	 * (SWBom).
	 * 
	 * @param bom         SWBom to add the metadata component to.
	 * @param name        String value to set the name of the metadata component to.
	 * @param toolName    String value to assign to the tool's name.
	 * @param toolVersion String value to assign the tool's version.
	 * @param group       String value to set the group of the metadata component to.
	 * @param version     String value to set the version of the metadata component to.
	 * @param type        String value of the Type to set the Type enumeration for the metadata
	 *                    component to.
	 */
	public static void addNewOuterComponent(Bom bom, String toolName, String toolVersion,
			String name, String group, String version, String type)
	{
		Component comp = null;
		
		if (StringUtils.isValid(name))
		{
			comp = new Component();
			comp.setName(name);
			
			if (StringUtils.isValid(group))
				comp.setGroup(group);
			
			if (StringUtils.isValid(version))
				comp.setVersion(version);
			
			if (StringUtils.isValid(type))
			{
				comp.setType(TypeUtils.lookupType(type));
			}
			else
				comp.setType(Type.CONTAINER);
		}
		Metadata metadata = new Metadata();
		metadata.setTimestamp(DateUtils.rightNowDate());
		
		// New Tool used to create the outer Object.
		Tool combiner = new Tool();
		combiner.setName(toolName);
		combiner.setVendor("Lockheed Martin");
		combiner.setVersion(toolVersion);
		
		List<Tool> completeToolList = new ArrayList<Tool>();
		completeToolList.add(combiner);
		
		// Add the rest of the tools.
		if ((bom.getMetadata() != null) && (bom.getMetadata().getTools() != null) &&
				(!bom.getMetadata().getTools().isEmpty()))
		{
			completeToolList = ToolsUtils.addUniqueTools(completeToolList,
					bom.getMetadata().getTools());
		}
		metadata.setTools(completeToolList);
		
		if (comp != null)
			metadata.setComponent(comp);
		else if ((bom.getMetadata() != null) && (bom.getMetadata().getComponent() != null))
			metadata.setComponent(bom.getMetadata().getComponent());
		bom.setMetadata(metadata);
	}
	
	/**
	 * (U) This method attempts to lookup the jar's version based on its jar sub name.
	 * 
	 * @param jarSubName String value of the beginning of the jar to lookup its version.
	 * @return String either the version of the jar or unknown if we can NOT determine the version
	 *         of the jar.
	 */
	public static String getJarVersion(String jarSubName)
	{
		String jarVersion = "unknown";
		try
		{
			String path = DateUtils.class.getProtectionDomain().getCodeSource().getLocation()
					.getPath();
			
			// String jarSub = "SBomCombinerService-";
			int index = path.indexOf(jarSubName);
			int endIndex = path.indexOf(".jar");
			
			jarVersion = path.substring(index + jarSubName.length(), endIndex);
			
			logger.debug(jarSubName + "Version: " + jarVersion);
		}
		catch (Exception e)
		{
			logger.warn("Unable to determine verison of " + jarSubName +
					"!  Setting it to unknown.");
		}
		return jarVersion;
	}
	
	/**
	 * This convenience method is used to print a component out as a String. It is here because we
	 * do NOT control Component. It really should be in the class.
	 * 
	 * @param component CycloneDx Component to make a String of.
	 * @return String representation of the component passed in.
	 */
	public static String toString(Component component)
	{
		return toString(component, "");
	}
	
	/**
	 * This convenience method is used to print a component out as a String. It is here because we
	 * do NOT control Component. It really should be in the class.
	 * 
	 * @param component CycloneDx Component to make a String of.
	 * @param tabs      String value of tabs to make it look nice.
	 * @return String representation of the component passed in.
	 */
	public static String toString(Component component, String tabs)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(tabs + "Name:" + component.getName() + "\n");
		sb.append(tabs + "Group: " + component.getGroup() + "\n");
		sb.append(tabs + "Version: " + component.getVersion() + "\n");
		
		return sb.toString();
	}
}
