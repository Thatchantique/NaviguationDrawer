package com.example.guillaume.naviguationdrawer;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guillaume.naviguationdrawer.loader.ProductLoader;
import com.example.guillaume.naviguationdrawer.loader.StoreLoader;
import com.example.guillaume.naviguationdrawer.model.Product;
import com.example.guillaume.naviguationdrawer.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
    private ListView listViewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        listViewProducts = (ListView) findViewById(R.id.list_view_products);

        if (NetworkUtils.isOnline(getApplicationContext())) {
            getLoaderManager().initLoader(1, null, this).forceLoad();
        } else {
            Toast.makeText(this, "You're not connected, shame on you", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        return new ProductLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object object) {
        String data = (String) object;
        if (data.length() > 0) {
            Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
            ArrayList<Product> products = new Gson().fromJson(data, listType);

            ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), R.layout.product_item, products);
            listViewProducts.setAdapter(productAdapter);
            productAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
        Log.e("SimpleNetworkFragment", "Indispo.");
    }
}
