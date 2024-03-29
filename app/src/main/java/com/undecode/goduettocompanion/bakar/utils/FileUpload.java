package com.undecode.goduettocompanion.bakar.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.undecode.goduettocompanion.interfaces.Constants;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.RequestBody;

public class FileUpload extends AsyncTask<Void, Integer, String>
{
    //private String filePath;
    private Bitmap bitmap;
    private Long totalSize;
    private ProgressDialog progressDialog;

    public FileUpload(Bitmap filePath, ProgressDialog progressDialog)
    {
        this.bitmap = filePath;
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onPreExecute()
    {
        // setting progress bar to zero
//        progressBar.setProgress(0);
//        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        // Making progress bar visible
        //progressBar.setVisibility(View.VISIBLE);

        // updating progress bar value
        //progressBar.setProgress(progress[0]);

        // updating percentage value
        //txtPercentage.setText(String.valueOf(progress[0]) + "%");
    }

    @Override
    protected String doInBackground(Void... params) {
        //return uploadFile();
        return ImageDownloader.uploadImage(bitmap);
    }

    @SuppressWarnings("deprecation")
    private String uploadFile() {
        String responseString = null;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Constants.API.POST_FILE);

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener()
                    {
                        @Override
                        public void transferred(long num)
                        {
                            publishProgress((int) ((num / (float) totalSize) * 100));
                        }
                    });

//            File sourceFile = new File(filePath);
//            RequestBody imageBody = FileUtils.serializeImage(filePath);

            // Adding file data to http body
            //entity.addPart("image", new FileBody(sourceFile));
            //entity.addPart("image", new FileBody(sourceFile));

            // Extra parameters if you want to pass to server
            //entity.addPart("website", new StringBody("www.androidhive.info"));
            //entity.addPart("email", new StringBody("abc@gmail.com"));

            totalSize = entity.getContentLength();
            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);
            } else {
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }

        } catch (ClientProtocolException e) {
            responseString = e.toString();
        } catch (IOException e) {
            responseString = e.toString();
        }

        Log.wtf("Bakar Upload File", "Response from server: " + responseString);
        return responseString;

    }

    @Override
    protected void onPostExecute(String result) {
        //Log.wtf("Bakar Upload File", "Response from server: " + result);
        System.out.println("Bakar Upload File Response from server: " + result);

        // showing the server response in an alert dialog
        //showAlert(result);
        progressDialog.dismiss();
        super.onPostExecute(result);
    }
}
