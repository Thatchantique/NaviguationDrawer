package com.example.guillaume.naviguationdrawer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 26/03/2017.
 */
public class ProductOrder {
    @SerializedName("Id")
    private int id;
    @SerializedName("Nb")
    private int nb;
    @SerializedName("Nom")
    private String nom;

    public ProductOrder(int nb, String nom) {
        this.id = -1;
        this.nb = nb;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "ProductOrder [ " +
                "id=" + id +
                ", nb='" + nb + '\'' +
                ", nom=" + nom +
                " ]";
    }
}