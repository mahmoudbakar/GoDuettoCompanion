package com.undecode.goduettocompanion.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class EducationItem{

	@SerializedName("ESuppotingDocument")
	private String eSuppotingDocument;

	@SerializedName("ELID")
	private String eLID;

	@SerializedName("EducationLevelName")
	private String educationLevelName;

	@SerializedName("EMID")
	private String eMID;

	@SerializedName("EducationMajorName")
	private String educationMajorName;

	@SerializedName("CMID")
	private String cMID;

	@SerializedName("ID")
	private String iD;

	@SerializedName("EGID")
	private String eGID;

	@SerializedName("EducationGrantingBodyName")
	private String educationGrantingBodyName;

	public void setESuppotingDocument(String eSuppotingDocument){
		this.eSuppotingDocument = eSuppotingDocument;
	}

	public String getESuppotingDocument(){
		return eSuppotingDocument;
	}

	public void setELID(String eLID){
		this.eLID = eLID;
	}

	public String getELID(){
		return eLID;
	}

	public void setEducationLevelName(String educationLevelName){
		this.educationLevelName = educationLevelName;
	}

	public String getEducationLevelName(){
		return educationLevelName;
	}

	public void setEMID(String eMID){
		this.eMID = eMID;
	}

	public String getEMID(){
		return eMID;
	}

	public void setEducationMajorName(String educationMajorName){
		this.educationMajorName = educationMajorName;
	}

	public String getEducationMajorName(){
		return educationMajorName;
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

	public void setEGID(String eGID){
		this.eGID = eGID;
	}

	public String getEGID(){
		return eGID;
	}

	public void setEducationGrantingBodyName(String educationGrantingBodyName){
		this.educationGrantingBodyName = educationGrantingBodyName;
	}

	public String getEducationGrantingBodyName(){
		return educationGrantingBodyName;
	}

	@Override
 	public String toString(){
		return 
			"EducationItem{" + 
			"eSuppotingDocument = '" + eSuppotingDocument + '\'' + 
			",eLID = '" + eLID + '\'' + 
			",educationLevelName = '" + educationLevelName + '\'' + 
			",eMID = '" + eMID + '\'' + 
			",educationMajorName = '" + educationMajorName + '\'' + 
			",cMID = '" + cMID + '\'' + 
			",iD = '" + iD + '\'' + 
			",eGID = '" + eGID + '\'' + 
			",educationGrantingBodyName = '" + educationGrantingBodyName + '\'' + 
			"}";
		}
}