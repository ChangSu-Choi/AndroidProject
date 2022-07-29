package com.example.recyclerview;

public class PaintTitle {

    public int imageId;
    public String title, price, brand;

    public   PaintTitle(int id, String str, String price, String brand){
        imageId = id;
        title=str;
        this.price = price;
        this.brand = brand;
    }
}
