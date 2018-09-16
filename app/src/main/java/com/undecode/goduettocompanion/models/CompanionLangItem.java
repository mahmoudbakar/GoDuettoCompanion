package com.undecode.goduettocompanion.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CompanionLangItem{

	@SerializedName("CMID")
	private String cMID;

	@SerializedName("LanguageName")
	private String languageName;

	@SerializedName("ID")
	private String iD;

	@SerializedName("Profeciency")
	private int profeciency;

	@SerializedName("LanguageID")
	private String languageID;

	public void setCMID(String cMID){
		this.cMID = cMID;
	}

	public String getCMID(){
		return cMID;
	}

	public void setLanguageName(String languageName){
		this.languageName = languageName;
	}

	public String getLanguageName(){
		return languageName;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	public void setProfeciency(int profeciency){
		this.profeciency = profeciency;
	}

	public int getProfeciency(){
		return profeciency;
	}

	public void setLanguageID(String languageID){
		this.languageID = languageID;
	}

	public String getLanguageID(){
		return languageID;
	}

	@Override
 	public String toString(){
		return 
			"CompanionLangItem{" + 
			"cMID = '" + cMID + '\'' + 
			",languageName = '" + languageName + '\'' + 
			",iD = '" + iD + '\'' + 
			",profeciency = '" + profeciency + '\'' + 
			",languageID = '" + languageID + '\'' + 
			"}";
		}
}