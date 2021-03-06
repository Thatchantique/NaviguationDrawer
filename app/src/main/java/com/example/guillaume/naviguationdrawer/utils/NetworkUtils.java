package com.example.guillaume.naviguationdrawer.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

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

    public static String getData(String url) {
        String data = "";
        BufferedReader buffReader = null;
        HttpURLConnection urlConnection = null;

        try {
            URL driveURL = new URL(url);
            urlConnection = (HttpURLConnection) driveURL.openConnection();
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            return readResponse(inputStream);
        } catch (IOException e) {
            Log.e("WebsiteLoader", e.getMessage());
        }
        return data;
    }

    public static String postData(String url, String encodedData) {
        String result = "";
        URLConnection connection;

        Log.e("NetworkUtils", url);
        try {
            connection = new URL(url).openConnection();
            HttpURLConnection httpcon = buildHttpURLConnection(connection);

            result = getString(encodedData, httpcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String postDataDelete(String url, String encodedData) {
        String result = "";
        URLConnection connection;

        Log.e("NetworkUtils", url);
        try {
            connection = new URL(url).openConnection();
            HttpURLConnection httpcon = buildHttpURLConnectionForDelete(connection);

            result = getString(encodedData, httpcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getString(String encodedData, HttpURLConnection httpcon) throws Exception {
        String result;

        byte[] outputBytes = encodedData.getBytes("UTF-8");
        OutputStream os = httpcon.getOutputStream();
        os.write(outputBytes);
        os.close();

        if (httpcon.getResponseCode() == 200 || httpcon.getResponseCode() == 204) {
            result = readResponse(httpcon.getInputStream());
        } else {
            throw new Exception("An error occurred... " + httpcon.getResponseCode());
        }
        return result;
    }

    private static HttpURLConnection buildHttpURLConnection(URLConnection connection) throws IOException {
        HttpURLConnection httpcon = (HttpURLConnection) (connection);

        httpcon.setRequestProperty("Content-Type", "application/json");
        httpcon.setRequestProperty("Accept", "application/json");
        httpcon.setRequestMethod("POST");
        httpcon.setDoOutput(true);
        httpcon.connect();

        return httpcon;
    }

    private static HttpURLConnection buildHttpURLConnectionForDelete(URLConnection connection) throws IOException {
        HttpURLConnection httpcon = (HttpURLConnection) (connection);
        httpcon.setRequestMethod("DELETE");
        httpcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
        httpcon.connect();
        return httpcon;
    }
}