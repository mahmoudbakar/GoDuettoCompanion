package com.undecode.goduettocompanion.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LanguageModel{

	@SerializedName("LanguageName")
	private String languageName;

	@SerializedName("ID")
	private String iD;

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

	@Override
 	public String toString(){
		return 
			"LanguageModel{" + 
			"languageName = '" + languageName + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}