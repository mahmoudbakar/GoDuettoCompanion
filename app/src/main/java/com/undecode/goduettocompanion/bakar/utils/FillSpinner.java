package com.undecode.goduettocompanion.bakar.utils;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.undecode.goduettocompanion.R;

/**
 * Created by mahmo on 4/17/2018.
 */

public class FillSpinner
{
    public void fillSpinner (ArrayAdapter arrayAdapter, Spinner spinner)
    {
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
