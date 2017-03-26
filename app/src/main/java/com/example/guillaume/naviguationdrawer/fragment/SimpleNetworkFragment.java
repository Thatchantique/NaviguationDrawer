package com.example.guillaume.naviguationdrawer.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.example.guillaume.naviguationdrawer.R;
import com.example.guillaume.naviguationdrawer.WebsiteLoader;


public class SimpleNetworkFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_network, container, false);

        webView = (WebView) view.findViewById(R.id.web_view_reader);

        getLoaderManager().initLoader(1, null, this);

        Button buttonNetwork = (Button) view.findViewById(R.id.button_click_network);
        buttonNetwork.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                setLoaderManager();
            }
        });

        return view;
    }

    public void setLoaderManager() {
        Bundle url = new Bundle();
        url.putCharSequence("url", "https://www.google.fr");
        getLoaderManager().restartLoader(1, url, this).forceLoad();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        if (args == null) {
            return new WebsiteLoader(getActivity(), "");
        }
        Log.e("SimpleNetworkFragment", "onCreateLoader");
        return new WebsiteLoader(getActivity(), args.getString("url"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data.length() > 0) {
            webView.loadData(data, "text/html", "UTF-8");
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.e("SimpleNetworkFragment", "Indispo.");
    }
}
