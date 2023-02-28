package com.priti.Pharmeds;

public class Healthlibrarymodelclass {
    private int image;
    private String text;

    public Healthlibrarymodelclass() {
    }

    public Healthlibrarymodelclass(int image, String text) {
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
