package com.priti.Pharmeds;

public class ProductModel {
    String categories;
    String medname;
    String mfgname;
    String price;
    String mfgdate;
    String expdate;
    String meddescription;
    String fileurl;
    String rx;
    public String getFileurl() {
        return fileurl;
    }

    public String getRx() {
        return rx;
    }

    public ProductModel(String categories, String medname, String mfgname, String price, String mfgdate, String expdate, String meddescription,String rx, String fileurl) {
        this.categories = categories;
        this.medname = medname;
        this.mfgname = mfgname;
        this.price = price;
        this.mfgdate = mfgdate;
        this.expdate = expdate;
        this.meddescription = meddescription;
        this.fileurl = fileurl;
        this.rx = rx;
    }

    public ProductModel() {
    }

    public String getCategories() {
        return categories;
    }

    public String getMedname() {
        return medname;
    }

    public String getMfgname() {
        return mfgname;
    }

    public String getPrice() {
        return price;
    }

    public String getMfgdate() {
        return mfgdate;
    }

    public String getExpdate() {
        return expdate;
    }

    public String getMeddescription() {
        return meddescription;
    }

}
