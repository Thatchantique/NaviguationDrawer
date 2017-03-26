package com.example.guillaume.naviguationdrawer.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.guillaume.naviguationdrawer.utils.NetworkUtils;


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
    private String arg;

    public ProductLoader(Context context, Methods currentMethod, String arg) {
        super(context);
        this.currentMethod = currentMethod;

        switch (currentMethod) {
            case GET:
                url = baseURL + "liste";
                break;
            case DELETE:
                url = baseURL + "delete&id=" + arg;
                break;
            case SHOW_ORDER:
                break;
            case CREATE_ORDER:
                break;
            case ADD_PRODUCT_TO_ORDER:
                url = baseURL + "add";
                break;
        }

        this.arg = arg;
    }

    @Override
    public Object loadInBackground() {
        switch (currentMethod) {
            case GET:
                return NetworkUtils.getData(url);
            case DELETE:
                return NetworkUtils.getData(url);
            case SHOW_ORDER:
                return "";
            case CREATE_ORDER:
                return "";
            case ADD_PRODUCT_TO_ORDER:
                return NetworkUtils.postData(url, arg);
            default:
                return "";
        }
    }
}
