package com.example.guillaume.naviguationdrawer;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;

import java.nio.charset.MalformedInputException;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.guillaume.naviguationdrawer.action.FOO";
    public static final String ACTION_BAZ = "com.example.guillaume.naviguationdrawer.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.guillaume.naviguationdrawer.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.guillaume.naviguationdrawer.extra.PARAM2";

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
