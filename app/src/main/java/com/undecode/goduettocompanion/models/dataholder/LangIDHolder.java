package com.undecode.goduettocompanion.models.dataholder;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LangIDHolder{

	@SerializedName("lang")
	private String lang;

	public void setLang(String lang){
		this.lang = lang;
	}

	public String getLang(){
		return lang;
	}

	@Override
 	public String toString(){
		return 
			"LangIDHolder{" + 
			"lang = '" + lang + '\'' + 
			"}";
		}
}