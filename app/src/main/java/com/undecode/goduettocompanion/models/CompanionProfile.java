package com.undecode.goduettocompanion.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CompanionProfile{

	@SerializedName("CAcceptsCats")
	private boolean cAcceptsCats;

	@SerializedName("CZIP")
	private String cZIP;

	@SerializedName("education")
	private List<EducationItem> education;

	@SerializedName("CAcceptsChildren")
	private boolean cAcceptsChildren;

	@SerializedName("CGender")
	private boolean cGender;

	@SerializedName("CArrangeForVehicle")
	private boolean cArrangeForVehicle;

	@SerializedName("CPicture")
	private String cPicture;

	@SerializedName("experience")
	private List<ExperienceItem> experience;

	@SerializedName("CEmail")
	private String cEmail;

	@SerializedName("UserStatus")
	private int userStatus;

	@SerializedName("CAddress")
	private String cAddress;

	@SerializedName("CState")
	private String cState;

	@SerializedName("CHeight")
	private int cHeight;

	@SerializedName("CUsername")
	private String cUsername;

	@SerializedName("CAcceptsDogs")
	private boolean cAcceptsDogs;

	@SerializedName("ID")
	private String iD;

	@SerializedName("ActivationCode")
	private int activationCode;

	@SerializedName("CMaxGroup")
	private int cMaxGroup;

	@SerializedName("isFavourite")
	private boolean isFavourite;

	@SerializedName("workingHours")
	private List<WorkingHoursItem> workingHours;

	@SerializedName("CCity")
	private String cCity;

	@SerializedName("CInstantBooking")
	private boolean cInstantBooking;

	@SerializedName("CNotes")
	private String cNotes;

	@SerializedName("CDOB")
	private String cDOB;

	@SerializedName("CWeight")
	private int cWeight;

	@SerializedName("CCountry")
	private String cCountry;

	@SerializedName("CpaymentMethod")
	private int cpaymentMethod;

	@SerializedName("CLevel")
	private int cLevel;

	@SerializedName("CRegistrationDate")
	private String cRegistrationDate;

	@SerializedName("CPassword")
	private String cPassword;

	@SerializedName("CMobile")
	private String cMobile;

	@SerializedName("companionLang")
	private List<CompanionLangItem> companionLang;

	@SerializedName("CName")
	private String cName;

	public void setCAcceptsCats(boolean cAcceptsCats){
		this.cAcceptsCats = cAcceptsCats;
	}

	public boolean isCAcceptsCats(){
		return cAcceptsCats;
	}

	public void setCZIP(String cZIP){
		this.cZIP = cZIP;
	}

	public String getCZIP(){
		return cZIP;
	}

	public void setEducation(List<EducationItem> education){
		this.education = education;
	}

	public List<EducationItem> getEducation(){
		return education;
	}

	public void setCAcceptsChildren(boolean cAcceptsChildren){
		this.cAcceptsChildren = cAcceptsChildren;
	}

	public boolean isCAcceptsChildren(){
		return cAcceptsChildren;
	}

	public void setCGender(boolean cGender){
		this.cGender = cGender;
	}

	public boolean isCGender(){
		return cGender;
	}

	public void setCArrangeForVehicle(boolean cArrangeForVehicle){
		this.cArrangeForVehicle = cArrangeForVehicle;
	}

	public boolean isCArrangeForVehicle(){
		return cArrangeForVehicle;
	}

	public void setCPicture(String cPicture){
		this.cPicture = cPicture;
	}

	public String getCPicture(){
		return cPicture;
	}

	public void setExperience(List<ExperienceItem> experience){
		this.experience = experience;
	}

	public List<ExperienceItem> getExperience(){
		return experience;
	}

	public void setCEmail(String cEmail){
		this.cEmail = cEmail;
	}

	public String getCEmail(){
		return cEmail;
	}

	public void setUserStatus(int userStatus){
		this.userStatus = userStatus;
	}

	public int getUserStatus(){
		return userStatus;
	}

	public void setCAddress(String cAddress){
		this.cAddress = cAddress;
	}

	public String getCAddress(){
		return cAddress;
	}

	public void setCState(String cState){
		this.cState = cState;
	}

	public String getCState(){
		return cState;
	}

	public void setCHeight(int cHeight){
		this.cHeight = cHeight;
	}

	public int getCHeight(){
		return cHeight;
	}

	public void setCUsername(String cUsername){
		this.cUsername = cUsername;
	}

	public String getCUsername(){
		return cUsername;
	}

	public void setCAcceptsDogs(boolean cAcceptsDogs){
		this.cAcceptsDogs = cAcceptsDogs;
	}

	public boolean isCAcceptsDogs(){
		return cAcceptsDogs;
	}

	public void setID(String iD){
		this.iD = iD;
	}

	public String getID(){
		return iD;
	}

	public void setActivationCode(int activationCode){
		this.activationCode = activationCode;
	}

	public int getActivationCode(){
		return activationCode;
	}

	public void setCMaxGroup(int cMaxGroup){
		this.cMaxGroup = cMaxGroup;
	}

	public int getCMaxGroup(){
		return cMaxGroup;
	}

	public void setIsFavourite(boolean isFavourite){
		this.isFavourite = isFavourite;
	}

	public boolean isIsFavourite(){
		return isFavourite;
	}

	public void setWorkingHours(List<WorkingHoursItem> workingHours){
		this.workingHours = workingHours;
	}

	public List<WorkingHoursItem> getWorkingHours(){
		return workingHours;
	}

	public void setCCity(String cCity){
		this.cCity = cCity;
	}

	public String getCCity(){
		return cCity;
	}

	public void setCInstantBooking(boolean cInstantBooking){
		this.cInstantBooking = cInstantBooking;
	}

	public boolean isCInstantBooking(){
		return cInstantBooking;
	}

	public void setCNotes(String cNotes){
		this.cNotes = cNotes;
	}

	public String getCNotes(){
		return cNotes;
	}

	public void setCDOB(String cDOB){
		this.cDOB = cDOB;
	}

	public String getCDOB(){
		return cDOB;
	}

	public void setCWeight(int cWeight){
		this.cWeight = cWeight;
	}

	public int getCWeight(){
		return cWeight;
	}

	public void setCCountry(String cCountry){
		this.cCountry = cCountry;
	}

	public String getCCountry(){
		return cCountry;
	}

	public void setCpaymentMethod(int cpaymentMethod){
		this.cpaymentMethod = cpaymentMethod;
	}

	public int getCpaymentMethod(){
		return cpaymentMethod;
	}

	public void setCLevel(int cLevel){
		this.cLevel = cLevel;
	}

	public int getCLevel(){
		return cLevel;
	}

	public void setCRegistrationDate(String cRegistrationDate){
		this.cRegistrationDate = cRegistrationDate;
	}

	public String getCRegistrationDate(){
		return cRegistrationDate;
	}

	public void setCPassword(String cPassword){
		this.cPassword = cPassword;
	}

	public String getCPassword(){
		return cPassword;
	}

	public void setCMobile(String cMobile){
		this.cMobile = cMobile;
	}

	public String getCMobile(){
		return cMobile;
	}

	public void setCompanionLang(List<CompanionLangItem> companionLang){
		this.companionLang = companionLang;
	}

	public List<CompanionLangItem> getCompanionLang(){
		return companionLang;
	}

	public void setCName(String cName){
		this.cName = cName;
	}

	public String getCName(){
		return cName;
	}

	@Override
 	public String toString(){
		return 
			"CompanionProfile{" + 
			"cAcceptsCats = '" + cAcceptsCats + '\'' + 
			",cZIP = '" + cZIP + '\'' + 
			",education = '" + education + '\'' + 
			",cAcceptsChildren = '" + cAcceptsChildren + '\'' + 
			",cGender = '" + cGender + '\'' + 
			",cArrangeForVehicle = '" + cArrangeForVehicle + '\'' + 
			",cPicture = '" + cPicture + '\'' + 
			",experience = '" + experience + '\'' + 
			",cEmail = '" + cEmail + '\'' + 
			",userStatus = '" + userStatus + '\'' + 
			",cAddress = '" + cAddress + '\'' + 
			",cState = '" + cState + '\'' + 
			",cHeight = '" + cHeight + '\'' + 
			",cUsername = '" + cUsername + '\'' + 
			",cAcceptsDogs = '" + cAcceptsDogs + '\'' + 
			",iD = '" + iD + '\'' + 
			",activationCode = '" + activationCode + '\'' + 
			",cMaxGroup = '" + cMaxGroup + '\'' + 
			",isFavourite = '" + isFavourite + '\'' + 
			",workingHours = '" + workingHours + '\'' + 
			",cCity = '" + cCity + '\'' + 
			",cInstantBooking = '" + cInstantBooking + '\'' + 
			",cNotes = '" + cNotes + '\'' + 
			",cDOB = '" + cDOB + '\'' + 
			",cWeight = '" + cWeight + '\'' + 
			",cCountry = '" + cCountry + '\'' + 
			",cpaymentMethod = '" + cpaymentMethod + '\'' + 
			",cLevel = '" + cLevel + '\'' + 
			",cRegistrationDate = '" + cRegistrationDate + '\'' + 
			",cPassword = '" + cPassword + '\'' + 
			",cMobile = '" + cMobile + '\'' + 
			",companionLang = '" + companionLang + '\'' + 
			",cName = '" + cName + '\'' + 
			"}";
		}
}