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
                // TODO: create dialog
                /*
                 * Créer layout dialogFragment
                 * Ajouter editext avec customlayout
                 * Set currentMethod
                 * Call StoreLoader avec ce qu'il faut (voir cours)
                 * Stocker ID, l'utiliser à la place des valeurs hardcoder (cf storeactivity)
                 */


            }
        });

        Button displayOrder = (Button) view.findViewById(R.id.button_display_order);
        displayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storeActivity = new Intent(getActivity(), StoreActivity.class);
                startActivity(storeActivity);
            }
        });

        return view;
    }
}