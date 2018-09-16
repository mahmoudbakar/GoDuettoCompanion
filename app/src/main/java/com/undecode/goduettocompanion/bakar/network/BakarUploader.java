package com.undecode.goduettocompanion.bakar.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

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

public class BakarUploader extends AsyncTask<Void, Integer, String>
{
    private ProgressDialog progressDialog;
	private String[] filesPaths;
	private long totalSize = 0;
	private String[] filesNames;
	private Context context;
	private int currentFile = 1;
	private OnUploadComplete listener;
	private boolean error = false;
	private String url;

    public BakarUploader(Context context, String url, String[] filePath, String[] fileName, OnUploadComplete listener)
    {
        this.url = url;
        this.context = context;
        this.filesPaths = filePath;
        this.filesNames = fileName;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute()
    {
        // setting progress bar to zero
        //progressBar.setProgress(0);
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Uploading");
        progressDialog.setMessage("Uploading "+String.valueOf(currentFile)+"/"+String.valueOf(filesPaths.length)+" Please Wait..");
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        if (progressDialog.getProgress() == 100)
        {
            currentFile = currentFile + 1;
            progressDialog.setProgress(0);
            progressDialog.setMessage("Uploading "+String.valueOf(currentFile)+"/"+String.valueOf(filesPaths.length)+" Please Wait..");
        }
        progressDialog.setProgress(progress[0]);
    }

    @Override
    protected String doInBackground(Void... params)
    {
        for (int i = 0; i < filesPaths.length; i++)
        {
            uploadFiles(i);
        }
        return "All Done.";
    }

    @SuppressWarnings("deprecation")
    private String uploadFiles(int position)
    {
        String responseString = null;

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        try
        {
            MultiPartEntity entity = new MultiPartEntity(
                    new MultiPartEntity.ProgressListener()
                    {

                        @Override
                        public void transferred(long num)
                        {
                            publishProgress((int) ((num / (float) totalSize) * 100));
                        }
                    });
            File sourceFile = new File(filesPaths[position]);
            // Adding file data to http body
            entity.addPart("image", new FileBody(sourceFile));
            entity.addPart("name", new StringBody(filesNames[position]));
            totalSize = entity.getContentLength();
            httppost.setEntity(entity);
            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200)
            {
                // Server response
                responseString = EntityUtils.toString(r_entity);
            } else
            {
                error = true;
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }
        } catch (ClientProtocolException e)
        {
            error = true;
            responseString = e.toString();
        } catch (IOException e)
        {
            error = true;
            responseString = e.toString();
        }
        return responseString;

    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        progressDialog.dismiss();
        if (!error)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("All Files Uploaded Successfully.");
            builder1.setCancelable(false);
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            listener.onUploadSuccess("uploaded.");
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else if (error)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Error Uploading Files \nCheck your internet connection\nand try again.");
            builder1.setCancelable(false);
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            listener.onUploadError("error.");
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        Log.e("Bakar Upload", "Response from server: " + result);
    }

}