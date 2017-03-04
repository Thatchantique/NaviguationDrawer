package com.example.guillaume.naviguationdrawer.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guillaume.naviguationdrawer.MainActivity;
import com.example.guillaume.naviguationdrawer.R;

/**
 * Created by Guillaume on 12/02/2017.
 */

public class HomeFragment extends android.support.v4.app.Fragment{
    //TODO Afficher les resultats de la bdd

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);

        TextView textViewMessageWelcome = (TextView) view.findViewById(R.id.WelcomeMsg);

        SharedPreferences sharedPreferences = ((MainActivity) getActivity()).getSharedPreferences();

        String welcomeMessage = sharedPreferences.getString("user", "user");
        //TODO Faire correspondre les bonnes valeurs aux préférences

        textViewMessageWelcome.setText(getResources().getString(R.string.user_name_hello, "username"));

        return view;
    }
}