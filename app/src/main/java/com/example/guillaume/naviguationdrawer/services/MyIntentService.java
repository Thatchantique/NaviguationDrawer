package com.example.guillaume.naviguationdrawer.services;

import android.app.IntentService;
import android.content.Intent;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;

public class MyIntentService extends IntentService {
    public static int tempsRestant;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int tmpMillisecondes = 1000;

        tempsRestant = 10;

        do
        {
            try {
                tempsRestant--;
                Thread.sleep(tmpMillisecondes);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(tempsRestant != 0);

        Message message = Message.obtain();

        Bundle bd = new Bundle();

        bd.putString("Message", "Coucou je suis un msg");
        //bd.putParcelable("key", (Parcelable) object); -> Si object perso !

        message.setData(bd);

        try {
            Messenger msg = intent.getParcelableExtra("msg");
            msg.send(message);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        //Log.v("MyIntentService","Arrive à 0");
    }
}
