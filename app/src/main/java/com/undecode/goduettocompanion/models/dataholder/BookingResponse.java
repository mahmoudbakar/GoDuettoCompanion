package com.undecode.goduettocompanion.models.dataholder;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BookingResponse{

	@SerializedName("CreateBookingResult")
	private String createBookingResult;

	public void setCreateBookingResult(String createBookingResult){
		this.createBookingResult = createBookingResult;
	}

	public String getCreateBookingResult(){
		return createBookingResult;
	}

	@Override
 	public String toString(){
		return 
			"BookingResponse{" + 
			"createBookingResult = '" + createBookingResult + '\'' + 
			"}";
		}
}