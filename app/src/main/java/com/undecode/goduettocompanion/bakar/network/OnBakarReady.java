package com.undecode.goduettocompanion.bakar.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface OnBakarReady
{
    public void onSuccess(JSONObject response);
    public void onError(VolleyError error);
}
