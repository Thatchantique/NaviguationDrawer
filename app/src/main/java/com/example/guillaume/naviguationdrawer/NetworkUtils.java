package com.example.guillaume.naviguationdrawer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Guillaume on 26/03/2017.
 */

public class NetworkUtils {
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String readResponse(InputStream inputStream) throws IOException {
        String line, result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((line = br.readLine()) != null) {
            result += line;
        }
        return result;
    }
}