package com.example.guillaume.naviguationdrawer.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
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

public class HomeFragment extends android.support.v4.app.Fragment {

    private TextView textViewRemainingTime;

    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_UNREGISTER_CLIENT = 2;
    public static final int MSG_GET_REMAINING_TIME = 3;

    private Messenger messengerService;
    private Messenger mailbox = new Messenger(new InconmingHandler());

    private class InconmingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET_REMAINING_TIME:
                    int tmpsRestant = msg.arg1;

                    String stringRemainingTime = (tmpsRestant < 10) ? "0" + tmpsRestant : String.valueOf(tmpsRestant);

                    Log.d("HomeFragment", "Voici le temps restant : " + tmpsRestant);
                    textViewRemainingTime.setText(getResources().getString(R.string.remaining_time, stringRemainingTime));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);

        Button buttonClick = (Button) view.findViewById(R.id.button_click);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBindTestBindingServiceClick(v);
            }
        });

        textViewRemainingTime = (TextView) view.findViewById(R.id.text_view_remaining_time);

        TextView textViewMessageWelcome = (TextView) view.findViewById(R.id.WelcomeMsg);

        SharedPreferences sharedPreferences = ((MainActivity) getActivity()).getSharedPreferences();

        String welcomeMessage = sharedPreferences.getString("user", "user");
        //TODO Faire correspondre les bonnes valeurs aux préférences

        textViewMessageWelcome.setText(getResources().getString(R.string.user_name_hello, "username"));

        return view;
    }

    private ServiceConnection connexion = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messengerService = new Messenger(service);

            // Si c'est un appel static, tu fais appel à la classe
            try {
                Message message = Message.obtain(null, MSG_REGISTER_CLIENT);
                message.replyTo = mailbox;
                messengerService.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            // textViewRemainingTime.setText(getResources().getString(R.string.remaining_time, mail.getTempsRestant()));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messengerService = null;
        }
    };

    public void onBindTestBindingServiceClick(View view) {
        getActivity().bindService(new Intent(getActivity(), BetterService.class), connexion, BIND_AUTO_CREATE);
    }

    public void onUnbindClick(View view) {
        getActivity().unbindService(connexion);
    }
}