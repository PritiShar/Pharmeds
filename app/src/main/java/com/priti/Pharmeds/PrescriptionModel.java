package com.priti.Pharmeds;

public class PrescriptionModel {
    String fileurl;

    public PrescriptionModel(String fileurl) {
        this.fileurl = fileurl;
    }

    public PrescriptionModel() {
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
