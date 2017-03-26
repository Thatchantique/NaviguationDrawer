package com.example.guillaume.naviguationdrawer;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Guillaume on 12/02/2017.
 */
public class DrivingSchool implements Serializable {
    private String id;
    private String name = "";
    private int icon = -1;
    private String adresse = "";

    public DrivingSchool(String adresse, int icon, String name) {
        this.id = UUID.randomUUID().toString();
        this.adresse = adresse;
        this.icon = icon;
        this.name = name;
    }
    public DrivingSchool(String adresse, String name) {
        this.id = UUID.randomUUID().toString();
        this.adresse = adresse;
        this.name = name;
    }

    public void updateDrivingSchool(DrivingSchool ds)
    {
        this.adresse = ds.getAdress();
        this.name = ds.getName();
        this.icon = ds.getIcon();
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

    public String getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DrivingSchool [ " +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", adresse='" + adresse + '\'' +
                " ]";
    }
}
