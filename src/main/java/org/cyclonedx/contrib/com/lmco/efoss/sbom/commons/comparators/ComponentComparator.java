/*
 * Copyright (c) 2018,2019 Lockheed Martin Corporation.
 *
 * This work is owned by Lockheed Martin Corporation. Lockheed Martin personnel are permitted to use and
 * modify this software.  Lockheed Martin personnel may also deliver this source code to any US Government
 * customer Agency under a "US Government Purpose Rights" license.
 *
 * See the LICENSE file distributed with this work for licensing and distribution terms
 */
package org.cyclonedx.contrib.com.lmco.efoss.sbom.commons.comparators;

import java.util.Comparator;

import org.cyclonedx.model.Component;

/**
 * (U) This custom Comparator is used to compare two Cyclone Dx Components. It is needed because the
 * Cyclone DX Compare method, is looking at all values in the Component. We are only concerned with
 * "name", "group", and "version".
 * 
 * @author wrgoff
 * @since 17 August 2020
 */
public class ComponentComparator implements Comparator<Component>
{
	/**
	 * (U) This method implements the "compare" function used to compare two Components.
	 * 
	 * @param component1 Component to compare.
	 * @param component2 Component to compare.
	 * @return int either 0, 1, or -1 depending on the compare.
	 */
	public int compare(Component component1, Component component2)
	{
		String artifact1 = component1.getName().trim();
		String artifact2 = component2.getName().trim();
		
		int compareValue = artifact1.compareTo(artifact2);
		
		if (compareValue == 0)
		{
			String group1 = "";
			
			if (component1.getGroup() != null)
				group1 = component1.getGroup().trim();
			
			String group2 = "";
			if (component2.getGroup() != null)
				group2 = component2.getGroup().trim();
			
			compareValue = group1.compareTo(group2);
			if (compareValue == 0)
			{
				String version1 = component1.getVersion().trim();
				String version2 = component2.getVersion().trim();
				compareValue = version1.compareTo(version2);
			}
		}
		return compareValue;
	}
	
	/**
	 * (U) This method is used to check to see if two components have the same artifact (name),
	 * group, and version.
	 * 
	 * @param component1 Component to compare.
	 * @param component2 Component to compare.
	 * @return boolean either true if the two components contain the same artifact, group and
	 *         version. Or false if they do NOT.
	 */
	public boolean equals(Component component1, Component component2)
	{
		boolean equal = false;
		
		if (compare(component1, component2) == 0)
			equal = true;
		
		return equal;
	}
}
