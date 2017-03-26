package com.example.guillaume.naviguationdrawer;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Guillaume on 26/03/2017.
 */

public class WebsiteLoader extends AsyncTaskLoader {
    private String url;

    public WebsiteLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public Object loadInBackground() {
        String pageResult = "";
        BufferedReader buffReader = null;
        HttpURLConnection urlConnection = null;

        try {
            URL iutC3URL = new URL(url);
            urlConnection = (HttpURLConnection) iutC3URL.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            pageResult = NetworkUtils.readResponse(inputStream);
        } catch (IOException e) {
            Log.e("WebsiteLoader", e.getMessage());
        }
        return pageResult;
    }
}
