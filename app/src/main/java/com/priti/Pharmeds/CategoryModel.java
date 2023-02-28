package com.priti.Pharmeds;

public class CategoryModel {
    String Category,Productdetails,fileurl;

    public CategoryModel(String category, String productdetails, String fileurl) {
        Category = category;
        Productdetails = productdetails;
        this.fileurl = fileurl;
    }

    public CategoryModel() {
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getProductdetails() {
        return Productdetails;
    }

    public void setProductdetails(String productdetails) {
        Productdetails = productdetails;
    }
}
