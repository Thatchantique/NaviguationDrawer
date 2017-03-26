package com.example.guillaume.naviguationdrawer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guillaume.naviguationdrawer.utils.NetworkUtils;
import com.example.guillaume.naviguationdrawer.model.Product;
import com.example.guillaume.naviguationdrawer.loader.ProductLoader;
import com.example.guillaume.naviguationdrawer.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Guillaume on 26/03/2017.
 */

public class ProductsFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {
    private ProductLoader.Methods currentMethod;
    private TextView result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        result = (TextView) view.findViewById(R.id.text_view_display);
        result.setText("teste");

        Button get = (Button) view.findViewById(R.id.button_get_list);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMethod = ProductLoader.Methods.GET;
                launchMethod();
            }
        });

        Button delete = (Button) view.findViewById(R.id.button_delete_product);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMethod = ProductLoader.Methods.DELETE;
                launchMethod();
            }
        });

        Button add  = (Button) view.findViewById(R.id.button_add_product);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentMethod = ProductLoader.Methods.ADD_PRODUCT_TO_ORDER;
                launchMethod();
            }
        });

        Button create = (Button) view.findViewById(R.id.button_create_order);
        Button show = (Button) view.findViewById(R.id.button_display_order);

        getLoaderManager().initLoader(1, null, this);

        return view;
    }

    public void setLoaderManager() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("method", currentMethod);

        // TODO: change to a dynamic ID
        switch (currentMethod) {
            case DELETE:
                bundle.putString("arg", "1");
                break;
            case ADD_PRODUCT_TO_ORDER:
                Product product = new Product("Persona 5", 70f);
                String jsonResult = new Gson().toJson(product, Product.class);
                bundle.putString("arg", jsonResult);
                break;
        }

        getLoaderManager().restartLoader(1, bundle, this).forceLoad();
    }

    private void launchMethod() {
        if(NetworkUtils.isOnline(getContext())) {
            setLoaderManager();
        } else {
            Toast.makeText(getActivity(), "You're not connected, shame on you", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        if (args == null) {
            return new ProductLoader(getActivity(), ProductLoader.Methods.GET, "");
        }
        String arg = (args.getString("arg") != null) ? args.getString("arg") : "";
        return new ProductLoader(getActivity(), (ProductLoader.Methods) args.getSerializable("method"), arg);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data.length() > 0) {
            switch (currentMethod) {
                case GET:
                    Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
                    ArrayList<Product> products = new Gson().fromJson(data, listType);
                    result.setText(buildResult(products));
                    break;
                case DELETE:
                    result.setText(data);
                    break;
                case ADD_PRODUCT_TO_ORDER:
                    result.setText(data);
                    break;
            }
        }
    }

    private String buildResult(ArrayList<Product> products) {
        String result = "";
        for (Product product : products) {
            result += product + "\n";

        }
        return result;
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.e("SimpleNetworkFragment", "Indispo.");
    }
}
