package com.undecode.goduettocompanion.bakar.network;

public interface OnUploadComplete
{
    public void onUploadSuccess(String result);
    public void onUploadError(String error);
}
