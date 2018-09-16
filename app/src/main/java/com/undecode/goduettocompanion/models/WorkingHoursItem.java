package com.undecode.goduettocompanion.models;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class WorkingHoursItem{

	@SerializedName("HourlyWages")
	private int hourlyWages;

	@SerializedName("DayOfWeek")
	private int dayOfWeek;

	@SerializedName("WorkStartTime")
	private String workStartTime;

	@SerializedName("WorkEndTime")
	private String workEndTime;

	@SerializedName("CMID")
	private String cMID;

	@SerializedName("ID")
	private String iD;

	public void setHourlyWages(int hourlyWages){
		this.hourlyWages = hourlyWages;
	}

	public int getHourlyWages(){
		return hourlyWages;
	}

	public void setDayOfWeek(int dayOfWeek){
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayOfWeek(){
		return dayOfWeek;
	}

	public void setWorkStartTime(String workStartTime){
		this.workStartTime = workStartTime;
	}

	public String getWorkStartTime(){
		return workStartTime;
	}

	public void setWorkEndTime(String workEndTime){
		this.workEndTime = workEndTime;
	}

	public String getWorkEndTime(){
		return workEndTime;
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
			"WorkingHoursItem{" + 
			"hourlyWages = '" + hourlyWages + '\'' + 
			",dayOfWeek = '" + dayOfWeek + '\'' + 
			",workStartTime = '" + workStartTime + '\'' + 
			",workEndTime = '" + workEndTime + '\'' + 
			",cMID = '" + cMID + '\'' + 
			",iD = '" + iD + '\'' + 
			"}";
		}
}