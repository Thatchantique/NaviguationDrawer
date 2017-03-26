package com.example.guillaume.naviguationdrawer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 26/03/2017.
 */
public class Product {
    @SerializedName("Id")
    private int id;
    @SerializedName("Nom")
    private String name;
    @SerializedName("Prix")
    private float prix;

    public Product(String name, float prix) {
        this.id = -1;
        this.name = name;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Product [ " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prix=" + prix +
                " ]";
    }
}