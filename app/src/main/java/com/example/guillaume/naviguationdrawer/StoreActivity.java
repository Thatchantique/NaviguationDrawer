package com.example.guillaume.naviguationdrawer;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.guillaume.naviguationdrawer.loader.ProductLoader;
import com.example.guillaume.naviguationdrawer.loader.StoreLoader;
import com.example.guillaume.naviguationdrawer.utils.NetworkUtils;

public class StoreActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
    private StoreLoader.Methods currentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Bundle bundle = new Bundle();
        bundle.putSerializable("method", StoreLoader.Methods.SHOW_ORDER);
        bundle.putString("arg", "0");

        if (NetworkUtils.isOnline(getApplicationContext())) {
            getLoaderManager().initLoader(1, bundle, this).forceLoad();
        } else {
            Toast.makeText(this, "You're not connected, shame on you", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<Object> onCreateLoader(int i, Bundle args) {
        if (args == null) {
            return new StoreLoader(this, StoreLoader.Methods.SHOW_ORDER, "-1");
        }
        String arg = (args.getString("arg") != null) ? args.getString("arg") : "";
        return new StoreLoader(this, (StoreLoader.Methods) args.getSerializable("method"), arg);
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object object) {
        String data = (String) object;
        if (data.length() > 0) {
            Log.e("StoreActivity", data);
            // Récupérer la currentMethod
            // Faire le switch dans le loadFinished
            // Afficher la commande actuelle (TextView, ListView pour chaque élémaent dans la commande

            // TODO:
            // Création de la commande ?
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
        Log.e("StoreActivity", "Indispo.");
    }
}
