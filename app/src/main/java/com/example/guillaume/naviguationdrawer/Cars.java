package com.example.guillaume.naviguationdrawer;

/**
 * Created by Gaujac on 15/02/2017.
 */

public class Cars {

    private String model;
    private String mark;
    private int registration;

    public Cars(String model, String mark, int registration) {
        this.model = model;
        this.mark = mark;
        this.registration = registration;
    }

    public Cars() {
    }

    public int getRegistration() {
        return registration;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "model=" + model +
                ", mark='" + mark + '\'' +
                ", registration='" + registration + '\'' +
                '}';
    }
}
