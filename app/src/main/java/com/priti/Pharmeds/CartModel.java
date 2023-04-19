package com.priti.Pharmeds;

public class CartModel {
    String fileUrl,pname,quantity,price;

    public CartModel() {
    }

    public CartModel(String fileUrl, String pname, String quantity, String price) {
        this.fileUrl = fileUrl;
        this.pname = pname;
        this.quantity = quantity;
        this.price = price;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getPname() {
        return pname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
