package com.example.guillaume.naviguationdrawer;

import java.io.Serializable;

/**
 * Created by Guillaume on 12/02/2017.
 */
public class DrivingSchool implements Serializable {
    private String name;
    private int icon = -1;
    private String adresse;

    public DrivingSchool(String adresse, int icon, String name) {
        this.adresse = adresse;
        this.icon = icon;
        this.name = name;
    }
    public DrivingSchool(String adresse, String name) {
        this.adresse = adresse;
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
