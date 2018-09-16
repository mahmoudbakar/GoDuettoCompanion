package com.undecode.goduettocompanion.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CitiesModel{

	@SerializedName("CityID")
	private String cityID;

	@SerializedName("ID")
	private String iD;

	@SerializedName("CityNameL")
	private String cityNameL;

	@SerializedName("LanguageID")
	private String languageID;

	public void setCityID(String cityID){
		this.cityID = cityID;
	}

	public String getCityID(){
		return cityID;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	public void setCityNameL(String cityNameL){
		this.cityNameL = cityNameL;
	}

	public String getCityNameL(){
		return cityNameL;
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
//			"CitiesModel{" +
//			"cityID = '" + cityID + '\'' +
//			",iD = '" + iD + '\'' +
//			",cityNameL = '" + cityNameL + '\'' +
//			",languageID = '" + languageID + '\'' +
//			"}";
//		}

	@Override
	public String toString(){
		return cityNameL;
	}
}