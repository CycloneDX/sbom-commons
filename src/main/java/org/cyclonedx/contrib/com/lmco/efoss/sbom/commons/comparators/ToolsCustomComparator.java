/*
 * Copyright (c) 2018,2019, 2020, 2021 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.comparators;

import java.util.Comparator;

import org.cyclonedx.model.Tool;

/**
 * (U) This class was generated to compare SBom Metadata Tools.
 * 
 * @author wrgoff
 * @since 24 February 2021
 */
public class ToolsCustomComparator implements Comparator<Tool>
{
	/**
	 * (U) This method implements the "compare" function used to compare two Tools.
	 * 
	 * @param tool1 Tool to compare.
	 * @param tool2 Tool to compare.
	 * @return int either 0, 1, or -1 depending on the compare.
	 */
	@Override
	public int compare(Tool tool1, Tool tool2)
	{
		String artifact1 = tool1.getName().trim();
		String artifact2 = tool2.getName().trim();
		
		int compareValue = artifact1.compareTo(artifact2);
		
		if (compareValue == 0)
		{
			String vender1 = "";
			
			if (tool1.getVendor() != null)
				vender1 = tool1.getVendor().trim();
			
			String vender2 = "";
			if (tool2.getVendor() != null)
				vender2 = tool2.getVendor().trim();
			
			compareValue = vender1.compareTo(vender2);
			if (compareValue == 0)
			{
				String version1 = "";
				if (tool1.getVersion() != null)
					version1 = tool1.getVersion().trim();
				
				String version2 = "";
				
				if (tool2.getVendor() != null)
					version2 = tool2.getVersion().trim();
				
				compareValue = version1.compareTo(version2);
			}
		}
		return compareValue;
	}
	
	/**
	 * (U) This method is used to check to see if two Tools have the same artifact (name), vender,
	 * and version.
	 * 
	 * @param tool1 Tool to compare.
	 * @param tool2 Tool to compare.
	 * @return boolean either true if the two Tools contain the same name, vender and version. Or
	 *         false if they do NOT.
	 */
	public boolean equals(Tool tool1, Tool tool2)
	{
		boolean equal = false;
		
		if (compare(tool1, tool2) == 0)
			equal = true;
		
		return equal;
	}
}
