package com.example.guillaume.naviguationdrawer;

import android.graphics.drawable.Drawable;

/**
 * Created by Guillaume on 12/02/2017.
 */

public class DrivingSchool {

    private String name;
    private int icon;
    private String adresse;

    public DrivingSchool(String adresse, int icon, String name) {
        this.adresse = adresse;
        this.icon = icon;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public String getAdress() {
        return adresse;
    }

    @Override
    public String toString() {
        return "DrivingSchool{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", adresse='" + adresse + '\'' +
                '}';
    }


}
