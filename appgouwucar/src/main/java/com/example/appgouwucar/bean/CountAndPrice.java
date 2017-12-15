package com.example.appgouwucar.bean;

/**
 * Created by lenovo on 2017/12/14.
 */

public class CountAndPrice {
    private double price;
    private int count;

    public CountAndPrice(double price, int count) {
        this.price = price;
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
