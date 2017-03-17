package com.example.guillaume.naviguationdrawer;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Created by Guillaume on 17/03/2017.
 */

public class BetterService extends Service {

    private int tempsRestant;

    Messenger msg;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        tempsRestant = 10;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int tmpMillisecondes = 1000;

                do
                {
                    try {
                        tempsRestant--;
                        Thread.sleep(tmpMillisecondes);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while(tempsRestant != 0);
            }
        }).start();


        return new Link();
    }

    public class Link extends Binder {
        public int getTempsRestant() {
            return tempsRestant;
        }
    }
}
