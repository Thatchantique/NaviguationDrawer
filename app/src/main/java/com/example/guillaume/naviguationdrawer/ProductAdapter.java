package com.example.guillaume.naviguationdrawer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guillaume.naviguationdrawer.model.Product;

import java.util.List;

/**
 * Created by lud00 on 05/04/17.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> products;

    public ProductAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_item, parent, false);
        }

        Product product = products.get(position);

        TextView textViewName = (TextView) convertView.findViewById(R.id.nom);
        textViewName.setText(product.getName());

        TextView textViewPrice = (TextView) convertView.findViewById(R.id.prix);
        textViewPrice.setText(product.getPrix() + " €");

        return convertView;
    }
}