package com.undecode.goduettocompanion.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CountriesLangModel{

	@SerializedName("CountryID")
	private String countryID;

	@SerializedName("CountryNameL")
	private String countryNameL;

	@SerializedName("ID")
	private String iD;

	@SerializedName("LanguageID")
	private String languageID;

	public void setCountryID(String countryID){
		this.countryID = countryID;
	}

	public String getCountryID(){
		return countryID;
	}

	public void setCountryNameL(String countryNameL){
		this.countryNameL = countryNameL;
	}

	public String getCountryNameL(){
		return countryNameL;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	public void setLanguageID(String languageID){
		this.languageID = languageID;
	}

	public String getLanguageID(){
		return languageID;
	}

//	@Override
// 	public String toString(){
//		return
//			"CountriesLangModel{" +
//			"countryID = '" + countryID + '\'' +
//			",countryNameL = '" + countryNameL + '\'' +
//			",iD = '" + iD + '\'' +
//			",languageID = '" + languageID + '\'' +
//			"}";
//		}

	@Override
	public String toString(){
		return countryNameL;
	}
}