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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * (U) This Data Transfer Object is used for the testing of the JSonUtils parse Json method.
 * 
 * @author wrgoff
 * @since 29 April 2021
 */
public class TestObj extends BaseDto implements Serializable, Comparable<TestObj>
{
	private static final long serialVersionUID = 5606156260158463556L;
	
	@JsonProperty("Dir")
	private String dir;
	
	@JsonProperty("Key")
	private String key;

	@JsonProperty("Source")
	private String source;
	
	@JsonProperty("Version")
	private String version;
	
	/**
	 * (U) Base constructor.
	 */
	public TestObj()
	{
	}
	
	/**
	 * (U) Convenience constructor.
	 * 
	 * @param key     String value to set the key to.
	 * @param source  String value to set the source to.
	 * @param version String value to set the version to.
	 * @param dir     String value to set the dir to.
	 */
	public TestObj(String key, String source, String version, String dir)
	{
		this.key = key;
		this.source = source;
		this.version = version;
		this.dir = dir;
	}
	
	/**
	 * (U) This method is used to compare this TestObj to another TestObj.
	 * 
	 * @param other TestObj to compare this module to.
	 * @return int either 0, 1, or -1 depending on the compare.
	 */
	@Override
	public int compareTo(TestObj other)
	{
		int comp = -1;
		if(other != null)
		{
			comp = compareStr(getKey(), other.getKey());
			if(comp == 0)
			{
				comp = compareStr(getVersion(), other.getVersion());
				if(comp == 0)
				{
					comp = compareStr(getSource(), other.getSource());
				}
			}
		}
		return comp;
	}
	
	/**
	 * (U) This method is used to see if the other module matches this one.
	 * 
	 * @param other Object to compare to this module.
	 * @return boolean true if the other module is the same as this one. False if they are
	 *         different.
	 */
	public boolean equals(Object other)
	{
		boolean same = false;
		if ((other instanceof TestObj) && (this.compareTo((TestObj) other) == 0))
				same = true;
		
		return same;
	}
	
	/**
	 * (U) This method is used to get the directory.
	 * 
	 * @return String value of the dir field.
	 */
	public String getDir()
	{
		return dir;
	}
	
	/**
	 * (U) This method is used to get the key.
	 * 
	 * @return String value of the key.
	 */
	public String getKey()
	{
		return key;
	}
	
	/**
	 * (U) This method is used to get the source.
	 * 
	 * @return String value of the source.
	 */
	public String getSource()
	{
		return source;
	}
	
	/**
	 * (U) This method is used to get the version.
	 * 
	 * @return String value of the version.
	 */
	public String getVersion()
	{
		return version;
	}
	
	/**
	 * (U) Returns a hash code value for this Module Object. This method is supported for the
	 * benefit of hash tables such as those provided by HashMap.
	 * 
	 * @return a hash code value for this object.
	 */
	@Override
	public int hashCode()
	{
		return (this.getKey().hashCode() + this.getVersion().hashCode());
	}
	
	/**
	 * (U) This method is used to set the directory field.
	 * 
	 * @param dir String value to set the directory field to.
	 */
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	
	/**
	 * (U) This method is used to set the key field.
	 * 
	 * @param key String value to set the key field to.
	 */
	public void setKey(String key)
	{
		this.key = key;
	}
	
	/**
	 * (U) This method is used to set the source field.
	 * 
	 * @param source String value to set the source field to.
	 */
	public void setSource(String source)
	{
		this.source = source;
	}
	
	/**
	 * (U) This method is used to set the version field.
	 * 
	 * @param version String value to set the version field to.
	 */
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	/**
	 * (U) Method used to printout the values of this Terraform Module.
	 *
	 * @return String nice readable string value of this Module
	 */
	@Override
	public String toString()
	{
		return (toString(""));
	}
	/**
	 * (U) Convenience method to make the string printed out indent as intended.
	 *
	 * @param tabs String value for the tabs used for indentation.
	 * @return String nice readable string value of this Terraform Module.
	 */
	public String toString(String tabs)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(tabs + "Dir: " + getDir() + "\n");
		sb.append(tabs + "Key: " + getKey() + "\n");
		sb.append(tabs + "Source: " + getSource() + "\n");
		sb.append(tabs + "Version: " + getVersion() + "\n");

		return (sb.toString());
	}
}