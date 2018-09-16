package com.undecode.goduettocompanion.models;

public class CountriesModel
{
    private String id, countryName, phonePrefix, flag;

    public CountriesModel(String id, String countryName, String phonePrefix, String flag)
    {
        this.id = id;
        this.countryName = countryName;
        this.phonePrefix = phonePrefix;
        this.flag = flag;
    }

    public String getId()
    {
        return id;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public String getPhonePrefix()
    {
        return phonePrefix;
    }

    public String getFlag()
    {
        return flag;
    }

    @Override
    public String toString()
    {
        return countryName;
    }
}
