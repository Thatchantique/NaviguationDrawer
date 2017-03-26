package com.example.guillaume.naviguationdrawer.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.guillaume.naviguationdrawer.fragment.HomeFragment;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.guillaume.naviguationdrawer.fragment.HomeFragment.MSG_REGISTER_CLIENT;
import static com.example.guillaume.naviguationdrawer.fragment.HomeFragment.MSG_UNREGISTER_CLIENT;

/**
 * Created by Guillaume on 17/03/2017.
 */

public class BetterService extends Service {
    private int tempsRestant = 10;
    private int tick = 1000;

    private Messenger client;
    private Messenger messenger = new Messenger(new InconmingHandler());
    private Timer timer = new Timer();

    @Override
    public void onCreate() {
        super.onCreate();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                onActionTick();
            }
        }, 250, tick);
    }

    private void onActionTick() {
        Log.e("BetterService", "onActionTick");
        if (tempsRestant <= 0) {
            timer.cancel();
            stopSelf();
        }
        try {
            Message remainingTimeMessage = Message.obtain(null, HomeFragment.MSG_GET_REMAINING_TIME, tempsRestant, 0);
            if(client != null) {
                client.send(remainingTimeMessage);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        tempsRestant--;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private class InconmingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    client = msg.replyTo;
                    break;
                case MSG_UNREGISTER_CLIENT:
                    client = null;
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.e("BetterService", "onDestroy");
        Toast.makeText(getApplicationContext(), "Le service est mort :'(", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}