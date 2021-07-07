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

import java.util.List;

import org.cyclonedx.model.Tool;

import org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.comparators.ToolsCustomComparator;

/**
 * @author wrgoff
 *
 * @since 24 February 2021
 */
public class ToolsUtils
{
	/**
	 * (U) This method is used to add any tools that are in the "moreTools" that are not already in
	 * the completeList to the complete list of tools.
	 * 
	 * @param completeList List of tools to add any more tools into.
	 * @param moreTools    List of tools to add (if not already in) the complete list of tools.
	 * @return List of unique tools.
	 */
	public static List<Tool> addUniqueTools(List<Tool> completeList, List<Tool> moreTools)
	{
		if ((moreTools != null) && (!moreTools.isEmpty()))
		{
			for (Tool tool : moreTools)
			{
				if (!ToolsUtils.toolsContainTool(tool, completeList))
					completeList.add(tool);
			}
		}
		return completeList;
	}
	
	/**
	 * (U) This method is used to look for a Tool in the list of Tools. It uses the custom
	 * "ToolsCustomComparator" to look for a component with the same name, Vender, and version.
	 * 
	 * @param toolToLookFor Tools we are looking for.
	 * @param tools         List of components to look for the tool in.
	 * @return boolean either true we found it. Or false we did not.
	 */
	public static boolean toolsContainTool(Tool toolToLookFor, List<Tool> tools)
	{
		ToolsCustomComparator comparator = new ToolsCustomComparator();
		
		if ((tools != null) && (!tools.isEmpty()))
		{
			for (Tool tool : tools)
			{
				if (comparator.equals(tool, toolToLookFor))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * This convenience method is used to print a tool out as a String. It is here because we do NOT
	 * control Tool. It really should be in the class.
	 * 
	 * @param tool CycloneDx Tool to make a String of.
	 * @return String representation of the tool passed in.
	 */
	public static String toString(Tool tool)
	{
		return toString(tool, "");
	}
	
	/**
	 * This convenience method is used to print a tool out as a String. It is here because we do NOT
	 * control Tool. It really should be in the class.
	 * 
	 * @param tool CycloneDx Tool to make a String of.
	 * @param tabs String value of tabs to make it look nice.
	 * @return String representation of the tool passed in.
	 */
	public static String toString(Tool tool, String tabs)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(tabs + "Tool Name:" + tool.getName() + "\n");
		sb.append(tabs + "Tool Vendor: " + tool.getVendor() + "\n");
		sb.append(tabs + "Tool Version: " + tool.getVersion() + "\n");
		
		return sb.toString();
	}
}
