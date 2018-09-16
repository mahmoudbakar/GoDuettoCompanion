package com.undecode.goduettocompanion.data;

import android.content.Context;

import com.undecode.goduettocompanion.models.CountriesModel;

import java.util.List;

public class Countries
{
    private static Countries instance;
    private List<CountriesModel> countriesModels;

    private Countries()
    {

    }

    public static Countries getInstance()
    {
        if (instance == null)
        {
            instance = new Countries();

        }
        return instance;
    }

    public void setCountriesModels(List<CountriesModel> countriesModels)
    {
        this.countriesModels = countriesModels;
    }

    public List<CountriesModel> getCountriesModels()
    {
        return countriesModels;
    }

}
