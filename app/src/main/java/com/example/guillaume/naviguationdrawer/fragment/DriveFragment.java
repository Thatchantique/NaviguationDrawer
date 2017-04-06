package com.example.guillaume.naviguationdrawer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.guillaume.naviguationdrawer.ProductsActivity;
import com.example.guillaume.naviguationdrawer.R;
import com.example.guillaume.naviguationdrawer.StoreActivity;

/**
 * Created by Guillaume on 26/03/2017.
 */

public class DriveFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drive, container, false);

        Button get = (Button) view.findViewById(R.id.button_get_list);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productActivity = new Intent(getActivity(), ProductsActivity.class);
                startActivity(productActivity);
            }
        });

        Button createOrder = (Button) view.findViewById(R.id.button_create_order);
        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storeActivity = new Intent(getActivity(), StoreActivity.class);
                startActivity(storeActivity);
            }
        });

        Button displayOrder = (Button) view.findViewById(R.id.button_display_order);
        displayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    /* public void setLoaderManager() {
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
        /**
         * String arg;
         * if(args.getString("arg") != null) {
         *  arg = args.getString("arg");
         * } else {
         *  arg = "";
         * }
         */
         /* String arg = (args.getString("arg") != null) ? args.getString("arg") : "";
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
    } */
}
