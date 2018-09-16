package com.undecode.goduettocompanion.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ExperienceItem{

	@SerializedName("FieldOfExperienceID")
	private String fieldOfExperienceID;

	@SerializedName("ExperienceFieldName")
	private String experienceFieldName;

	@SerializedName("NumberOfYears")
	private int numberOfYears;

	@SerializedName("CMID")
	private String cMID;

	@SerializedName("ID")
	private String iD;

	public void setFieldOfExperienceID(String fieldOfExperienceID){
		this.fieldOfExperienceID = fieldOfExperienceID;
	}

	public String getFieldOfExperienceID(){
		return fieldOfExperienceID;
	}

	public void setExperienceFieldName(String experienceFieldName){
		this.experienceFieldName = experienceFieldName;
	}

	public String getExperienceFieldName(){
		return experienceFieldName;
	}

	public void setNumberOfYears(int numberOfYears){
		this.numberOfYears = numberOfYears;
	}

	public int getNumberOfYears(){
		return numberOfYears;
	}

	public void setCMID(String cMID){
		this.cMID = cMID;
	}

	public String getCMID(){
		return cMID;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	@Override
 	public String toString(){
		return 
			"ExperienceItem{" + 
			"fieldOfExperienceID = '" + fieldOfExperienceID + '\'' + 
			",experienceFieldName = '" + experienceFieldName + '\'' + 
			",numberOfYears = '" + numberOfYears + '\'' + 
			",cMID = '" + cMID + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}