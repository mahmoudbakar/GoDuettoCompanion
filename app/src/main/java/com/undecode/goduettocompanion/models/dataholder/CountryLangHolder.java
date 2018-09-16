package com.undecode.goduettocompanion.models.dataholder;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CountryLangHolder{

	@SerializedName("lang")
	private String lang;

	@SerializedName("countryId")
	private String countryId;

	public void setLang(String lang){
		this.lang = lang;
	}

	public String getLang(){
		return lang;
	}

	public void setCountryId(String countryId){
		this.countryId = countryId;
	}

	public String getCountryId(){
		return countryId;
	}

	@Override
 	public String toString(){
		return 
			"CountryLangHolder{" + 
			"lang = '" + lang + '\'' + 
			",countryId = '" + countryId + '\'' + 
			"}";
		}
}