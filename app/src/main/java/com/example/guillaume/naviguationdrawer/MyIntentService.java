package com.example.guillaume.naviguationdrawer;

import android.app.IntentService;
import android.content.Intent;
import android.os.Messenger;
import android.util.Log;


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

        Log.v("MyIntentService","Arrive à 0");

    }
}
