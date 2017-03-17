package com.example.guillaume.naviguationdrawer.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.guillaume.naviguationdrawer.BetterService;
import com.example.guillaume.naviguationdrawer.MainActivity;
import com.example.guillaume.naviguationdrawer.R;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Guillaume on 12/02/2017.
 */

public class HomeFragment extends android.support.v4.app.Fragment{
    //TODO Afficher les resultats de la bdd

    TextView text_view_remaining_time;

    private Messenger mailbox = new Messenger(new MainActivity.());


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);

        Button button_click = (Button) view.findViewById(R.id.button_click);

        button_click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBindTestBindingServiceClick(v);
            }
        });

        text_view_remaining_time = (TextView) view.findViewById(R.id.text_view_remaining_time);

        TextView textViewMessageWelcome = (TextView) view.findViewById(R.id.WelcomeMsg);

        SharedPreferences sharedPreferences = ((MainActivity) getActivity()).getSharedPreferences();

        String welcomeMessage = sharedPreferences.getString("user", "user");
        //TODO Faire correspondre les bonnes valeurs aux préférences

        textViewMessageWelcome.setText(getResources().getString(R.string.user_name_hello, "username"));

        return view;
    }

    private ServiceConnection connexion = new ServiceConnection() {

        private BetterService.Link serve;


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // serve = (TestBindingService.Liant) service;
            serve = (BetterService.Link) service;
            text_view_remaining_time.setText(getResources().getString(R.string.remaining_time, serve.getTempsRestant()));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serve = null;
        }
    };

    public void onBindTestBindingServiceClick(View view) {
        getActivity().bindService(new Intent(getActivity(), BetterService.class), connexion, BIND_AUTO_CREATE);
        Log.d("onBind", "coucou");
    }

    public void onUnbindClick(View view) {
        getActivity().unbindService(connexion);
    }
    private class handlerBetter extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String recu = bundle.getString("Message");
            Log.d("handleMessage", recu);
        }
    }
}