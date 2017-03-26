package com.example.guillaume.naviguationdrawer;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Guillaume on 26/03/2017.
 */

public class ProductLoader extends AsyncTaskLoader {
    public enum Methods {
        GET,
        DELETE,
        SHOW_ORDER,
        CREATE_ORDER,
        ADD_PRODUCT_TO_ORDER
    }

    private String url;
    //Adr. du pc.
    private String baseURL = "http://192.168.1.26:8080/drive/?page=";
    private Methods currentMethod;

    public ProductLoader(Context context, Methods currentMethod) {
        super(context);
        this.currentMethod = currentMethod;

        switch (currentMethod) {
            case GET:
                url = baseURL + "liste";
                break;
            case DELETE:
                break;
            case SHOW_ORDER:
                break;
            case CREATE_ORDER:
                break;
            case ADD_PRODUCT_TO_ORDER:
                break;
        }

        Log.e("ProductLoader", url);
    }

    @Override
    public Object loadInBackground() {
        String pageResult = "";
        BufferedReader buffReader = null;
        HttpURLConnection urlConnection = null;

        try {
            URL driveURL = new URL(url);
            urlConnection = (HttpURLConnection) driveURL.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            pageResult = NetworkUtils.readResponse(inputStream);
        } catch (IOException e) {
            Log.e("WebsiteLoader", e.getMessage());
        }
        return pageResult;
    }
}
