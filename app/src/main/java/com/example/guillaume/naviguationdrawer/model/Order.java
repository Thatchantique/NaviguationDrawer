package com.example.guillaume.naviguationdrawer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("All")
    private List<ProductOrder> all;
    @SerializedName("Total")
    private int total;
    @SerializedName("id")
    private int id;
    @SerializedName("Name")
    private String name;

    public Order(List<ProductOrder> all, int total, int id, String name) {
        this.all = all;
        this.total = total;
        this.id = id;
        this.name = name;
    }

    public List<ProductOrder> getAll() {
        return all;
    }

    public void setAll(List<ProductOrder> all) {
        this.all = all;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "Order [ " +
                "all=" + all +
                ", total=" + total +
                ", id=" + id +
                ", name=" + name +
                " ]";
    }
}
