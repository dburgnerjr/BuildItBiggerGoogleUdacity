package com.danielburgnerjr.builditbiggergoogleudacity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.danielburgnerjr.builditbiggergoogleudacity.backend.myApi.MyApi; // cannot resolve symbol myApi

import java.io.IOException;

class AsyncJokeTask extends AsyncTask<MainActivityFragment, Void, String> {

    private static MyApi myApi = null;  // same comment
    private MainActivityFragment fragment;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private static final String LOCALHOST_IP_ADDRESS = "http://10.0.0.7:8080/_ah/api/";

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        if(myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),  //same comment
                    new AndroidJsonFactory(), null)

                    .setRootUrl(LOCALHOST_IP_ADDRESS)
                    .setGoogleClientRequestInitializer(abstractGoogleClientRequest ->
                            abstractGoogleClientRequest.setDisableGZipContent(true));

            myApi = builder.build();  // cannot resolve method build()
        }

        fragment = params[0];
        context = fragment.getActivity();

        try {
            return myApi.showJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, "Joke is: " + result, Toast.LENGTH_LONG).show();
        fragment.loadedJoke = result;
        fragment.launchDisplayJokeActivity();
    }
}