package com.example.guillaume.naviguationdrawer.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.guillaume.naviguationdrawer.utils.NetworkUtils;

/**
 * Created by Guillaume on 26/03/2017.
 */

public class ProductLoader extends AsyncTaskLoader {
    private String baseURL = "http://10.0.2.2";
    private String port = "3333";
    private String url = baseURL + ":" + port + "/Magasin/Drive/Liste";

    public ProductLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return NetworkUtils.getData(url);
    }
}