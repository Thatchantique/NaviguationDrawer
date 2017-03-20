package com.example.guillaume.naviguationdrawer;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SimpleNetworkFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_network, container, false);

        final WebView webView = (WebView) view.findViewById(R.id.web_view_reader);

        Button buttonNetwork = (Button) view.findViewById(R.id.button_click_network);
        buttonNetwork.setOnClickListener(new View.OnClickListener()

        {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                HttpURLConnection urlConnection = null;
                BufferedReader buffReader = null;

                try {
                    // ConnectivityManager mgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                    // Network net = mgr.getActiveNetwork();

                    URL iutC3URL = new URL("http://www.iutc3.unicaen.fr");

                    urlConnection = (HttpURLConnection) iutC3URL.openConnection();
                    urlConnection.connect();



                    InputStream inputStream = urlConnection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    /**
     * Test connection : ConnectivityManager
     */
    //TODO: Implementation !
    public void testConnection() {

    }
}
