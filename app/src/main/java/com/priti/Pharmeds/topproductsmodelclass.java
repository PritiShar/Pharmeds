package com.priti.Pharmeds;

public class topproductsmodelclass {
    private int image;
    private String text;

    public topproductsmodelclass() {
    }

    public topproductsmodelclass(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {


        return image;
    }

    public String getText() {
        return text;
    }
}
