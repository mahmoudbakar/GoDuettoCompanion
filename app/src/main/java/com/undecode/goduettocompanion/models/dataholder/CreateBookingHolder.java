package com.undecode.goduettocompanion.models.dataholder;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CreateBookingHolder{

	@SerializedName("dropOfLong")
	private double dropOfLong;

	@SerializedName("pickupAddress")
	private String pickupAddress;

	@SerializedName("dropOfLat")
	private double dropOfLat;

	@SerializedName("dropOfAdd")
	private String dropOfAdd;

	@SerializedName("travellerId")
	private String travellerId;

	@SerializedName("companionId")
	private String companionId;

	@SerializedName("startTime")
	private String startTime;

	@SerializedName("endTime")
	private String endTime;

	@SerializedName("pickUpLat")
	private double pickUpLat;

	@SerializedName("pickupLong")
	private double pickupLong;

	public void setDropOfLong(double dropOfLong){
		this.dropOfLong = dropOfLong;
	}

	public double getDropOfLong(){
		return dropOfLong;
	}

	public void setPickupAddress(String pickupAddress){
		this.pickupAddress = pickupAddress;
	}

	public String getPickupAddress(){
		return pickupAddress;
	}

	public void setDropOfLat(double dropOfLat){
		this.dropOfLat = dropOfLat;
	}

	public double getDropOfLat(){
		return dropOfLat;
	}

	public void setDropOfAdd(String dropOfAdd){
		this.dropOfAdd = dropOfAdd;
	}

	public String getDropOfAdd(){
		return dropOfAdd;
	}

	public void setTravellerId(String travellerId){
		this.travellerId = travellerId;
	}

	public String getTravellerId(){
		return travellerId;
	}

	public void setCompanionId(String companionId){
		this.companionId = companionId;
	}

	public String getCompanionId(){
		return companionId;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getStartTime(){
		return startTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return endTime;
	}

	public void setPickUpLat(double pickUpLat){
		this.pickUpLat = pickUpLat;
	}

	public double getPickUpLat(){
		return pickUpLat;
	}

	public void setPickupLong(double pickupLong){
		this.pickupLong = pickupLong;
	}

	public double getPickupLong(){
		return pickupLong;
	}

	@Override
 	public String toString(){
		return 
			"CreateBookingHolder{" + 
			"dropOfLong = '" + dropOfLong + '\'' + 
			",pickupAddress = '" + pickupAddress + '\'' + 
			",dropOfLat = '" + dropOfLat + '\'' + 
			",dropOfAdd = '" + dropOfAdd + '\'' + 
			",travellerId = '" + travellerId + '\'' + 
			",companionId = '" + companionId + '\'' + 
			",startTime = '" + startTime + '\'' + 
			",endTime = '" + endTime + '\'' + 
			",pickUpLat = '" + pickUpLat + '\'' + 
			",pickupLong = '" + pickupLong + '\'' + 
			"}";
		}
}