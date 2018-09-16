package com.undecode.goduettocompanion.models.dataholder;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class SearchQuery
{

	@SerializedName("date")
	private String date;

	@SerializedName("gender")
	private boolean gender;

	@SerializedName("hourlyrateLess")
	private int hourlyrateLess;

	@SerializedName("city")
	private String city;

	@SerializedName("hourlyrateMore")
	private int hourlyrateMore;

	@SerializedName("worksWithChildren")
	private boolean worksWithChildren;

	@SerializedName("lang")
	private String lang;

	@SerializedName("userId")
	private String userId;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setGender(boolean gender){
		this.gender = gender;
	}

	public boolean isGender(){
		return gender;
	}

	public void setHourlyrateLess(int hourlyrateLess){
		this.hourlyrateLess = hourlyrateLess;
	}

	public int getHourlyrateLess(){
		return hourlyrateLess;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setHourlyrateMore(int hourlyrateMore){
		this.hourlyrateMore = hourlyrateMore;
	}

	public int getHourlyrateMore(){
		return hourlyrateMore;
	}

	public void setWorksWithChildren(boolean worksWithChildren){
		this.worksWithChildren = worksWithChildren;
	}

	public boolean isWorksWithChildren(){
		return worksWithChildren;
	}

	public void setLang(String lang){
		this.lang = lang;
	}

	public String getLang(){
		return lang;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"SearchQuery{" + 
			"date = '" + date + '\'' + 
			",gender = '" + gender + '\'' + 
			",hourlyrateLess = '" + hourlyrateLess + '\'' + 
			",city = '" + city + '\'' + 
			",hourlyrateMore = '" + hourlyrateMore + '\'' + 
			",worksWithChildren = '" + worksWithChildren + '\'' + 
			",lang = '" + lang + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}