package com.example.guillaume.naviguationdrawer;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guillaume.naviguationdrawer.loader.StoreLoader;
import com.example.guillaume.naviguationdrawer.model.Order;
import com.example.guillaume.naviguationdrawer.utils.NetworkUtils;
import com.google.gson.Gson;

public class StoreActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
    private StoreLoader.Methods currentMethod;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        textViewResult = (TextView) findViewById(R.id.text_view_result);

        Button addButton = (Button) findViewById(R.id.addProduct);
        Button deleteButton = (Button) findViewById(R.id.deleteProduct);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: ajouter add product
            }
        });

        final StoreActivity storeActivity = this;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMethod = StoreLoader.Methods.DELETE;
                Bundle bundle = new Bundle();
                bundle.putSerializable("method", currentMethod);
                bundle.putString("arg", "0");

                if (NetworkUtils.isOnline(getApplicationContext())) {
                    getLoaderManager().restartLoader(1, bundle, storeActivity).forceLoad();
                } else {
                    Toast.makeText(storeActivity, "You're not connected, shame on you", Toast.LENGTH_SHORT).show();
                }
            }
        });

        currentMethod = StoreLoader.Methods.SHOW_ORDER;
        Bundle bundle = new Bundle();
        bundle.putSerializable("method", currentMethod);
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

            switch (currentMethod) {
                case CREATE_ORDER:
                    break;
                case SHOW_ORDER:
                    Order order = new Gson().fromJson(data, Order.class);
                    textViewResult.setText(order.toString());
                    break;
                case DELETE:
                    finish();
                    break;
                case ADD_PRODUCT_TO_ORDER:
                    break;
            }

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
