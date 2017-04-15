package com.example.guillaume.naviguationdrawer.loader;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import com.example.guillaume.naviguationdrawer.utils.NetworkUtils;

/**
 * Created by Guillaume on 26/03/2017.
 */
public class StoreLoader extends AsyncTaskLoader {
    public enum Methods {
        CREATE_ORDER,
        SHOW_ORDER,
        DELETE,
        ADD_PRODUCT_TO_ORDER
    }

    private String baseURL = "http://10.0.2.2";
    private String port = "3333";
    private String url = baseURL + ":" + port + "/Magasin/Drive/";

    private String arg; // TODO: à améliorer
    private Methods currentMethod;

    public StoreLoader(Context context, Methods currentMethod, String arg) {
        super(context);
        this.currentMethod = currentMethod;

        switch (currentMethod) {
            case CREATE_ORDER:
                break;
            case SHOW_ORDER:
                url = url + arg;
                break;
            case DELETE:
                url = url + arg;
                break;
            case ADD_PRODUCT_TO_ORDER:
                // url = baseURL + "add";
                break;
        }

        this.arg = arg;
    }

    @Override
    public Object loadInBackground() {
        switch (currentMethod) {
            case CREATE_ORDER:
                return NetworkUtils.postData(url, arg);
            case DELETE:
                return NetworkUtils.postDataDelete(url, arg);
            case SHOW_ORDER:
                return NetworkUtils.getData(url);
            case ADD_PRODUCT_TO_ORDER:
                return NetworkUtils.postData(url, arg);
            default:
                return "";
        }
    }
}