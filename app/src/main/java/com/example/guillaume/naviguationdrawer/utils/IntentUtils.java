package com.example.guillaume.naviguationdrawer.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Guillaume on 26/03/2017.
 */
public class IntentUtils {
    public static void sendEmailIntent(Context context) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        String[] to = {"21504004@etu.unicaen.fr"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Prise de contact");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Ceci est le texte");
        context.startActivity(Intent.createChooser(emailIntent, "Envoyer à..."));
    }

    public static void sendShareIntent(Context context) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Share this wonderful app !");
        context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
}
