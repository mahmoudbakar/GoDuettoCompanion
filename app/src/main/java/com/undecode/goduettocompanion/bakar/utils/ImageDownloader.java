package com.undecode.goduettocompanion.bakar.utils;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.undecode.goduettocompanion.interfaces.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Remonda on 5/3/2017.
 */

public class ImageDownloader {


    public void downloadImage(View view, String url, ImageView imageView)
    {
        //Glide.with(view).load(url).into(imageView);
    }

    public static String uploadImage(Bitmap image)
    {
        String result = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 40, stream);
        byte [] byte_arr = stream.toByteArray();
        String image_str = Base64.encodeToString(byte_arr, Base64.DEFAULT);
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("image", image_str);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(basicNameValuePair);
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Constants.API.POST_FILE);
            httppost.setEntity(new ByteArrayEntity(byte_arr));
            HttpResponse response = httpclient.execute(httppost);
            result = convertResponseToString(response);
        }catch(Exception e){
            e.printStackTrace();
            result = "Error in http connection";
        }
        Log.wtf("ImageUpload", result);
        return result;
    }

    private static String convertResponseToString(HttpResponse response) throws IllegalStateException, IOException {
        InputStream inputStream;
        String res = "";
        StringBuffer buffer = new StringBuffer();
        inputStream = response.getEntity().getContent();
        int contentLength = (int) response.getEntity().getContentLength(); //getting content length…..

        if (contentLength < 0){
        }
        else{
            byte[] data = new byte[512];
            int len = 0;
            try
            {
                while (-1 != (len = inputStream.read(data)) )
                {
                    buffer.append(new String(data, 0, len)); //converting to string and appending  to stringbuffer…..
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                inputStream.close(); // closing the stream…..
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            res = buffer.toString();     // converting stringbuffer to string…..

        }
        return res;
    }
}
