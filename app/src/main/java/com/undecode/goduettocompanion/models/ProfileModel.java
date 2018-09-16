package com.undecode.goduettocompanion.models;

import org.joda.time.DateTime;

import java.util.Date;

public class ProfileModel
{
    private static ProfileModel profileModel;
    private String id, name, userName, mobile, mobile2, email, password, profilePic, nationalityID, activationCode;
    private boolean gender;
    private DateTime birthDate;
    private int userStatus, tAccountStatus;
    private ProfileModel()
    {

    }

    public static ProfileModel getInstance()
    {
        if (profileModel == null)
        {
            profileModel = new ProfileModel();
        }
        return profileModel;
    }

    public void setProfileModel(String id, String name, String userName, String mobile, String mobile2,
                        String email, String password, String profilePic, String nationalityID,
                        String activationCode, boolean gender, DateTime birthDate, int userStatus,
                        int tAccountStatus)
    {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.mobile = mobile;
        this.mobile2 = mobile2;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.nationalityID = nationalityID;
        this.activationCode = activationCode;
        this.gender = gender;
        this.birthDate = birthDate;
        this.userStatus = userStatus;
        this.tAccountStatus = tAccountStatus;
    }

    public static ProfileModel getProfileModel()
    {
        return profileModel;
    }

    public static void setProfileModel(ProfileModel profileModel)
    {
        ProfileModel.profileModel = profileModel;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMobile2()
    {
        return mobile2;
    }

    public void setMobile2(String mobile2)
    {
        this.mobile2 = mobile2;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getProfilePic()
    {
        return profilePic;
    }

    public void setProfilePic(String profilePic)
    {
        this.profilePic = profilePic;
    }

    public String getNationalityID()
    {
        return nationalityID;
    }

    public void setNationalityID(String nationalityID)
    {
        this.nationalityID = nationalityID;
    }

    public String getActivationCode()
    {
        return activationCode;
    }

    public void setActivationCode(String activationCode)
    {
        this.activationCode = activationCode;
    }

    public boolean isGender()
    {
        return gender;
    }

    public void setGender(boolean gender)
    {
        this.gender = gender;
    }

    public DateTime getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate)
    {
        this.birthDate = birthDate;
    }

    public int getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus(int userStatus)
    {
        this.userStatus = userStatus;
    }

    public int gettAccountStatus()
    {
        return tAccountStatus;
    }

    public void settAccountStatus(int tAccountStatus)
    {
        this.tAccountStatus = tAccountStatus;
    }
}
