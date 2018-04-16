package com.asha.canadainfoapp.service;

import android.os.AsyncTask;

import com.asha.canadainfoapp.data.TitleData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Asha on 4/2/2018.
 *
 *
 */

public class ServiceAPI {
    private ServiceAPICallback serviceAPICallback;
    private Exception error;

    public ServiceAPI(ServiceAPICallback serviceAPICallback){
        this.serviceAPICallback = serviceAPICallback;
    }

    public void refreshAPI(){
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {

                String apiUrl = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
                try {
                    URL url = new URL(apiUrl);

                    //retrieves a URLConnection object and gets an input stream from the connection
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    // create a BufferedReader on the input stream and read from it
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while((line = reader.readLine()) != null){
                        result.append(line);
                    }
                    return result.toString();

                } catch (MalformedURLException e) {
                    error = e;
                } catch (IOException e){
                    error = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null){
                    serviceAPICallback.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(s);

                    TitleData titleData = new TitleData();
                    titleData.populator(jsonObject);


                    serviceAPICallback.serviceSuccess(titleData);

                } catch (JSONException e) {
                    serviceAPICallback.serviceFailure(e);
                }
            }
        }.execute();
    }
}
