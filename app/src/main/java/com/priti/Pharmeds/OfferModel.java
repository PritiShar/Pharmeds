package com.priti.Pharmeds;

public class OfferModel {
    String title,desc,fileurl,percentoff;

    public OfferModel() {
    }

    public OfferModel(String title, String desc, String fileurl, String percentoff) {
        this.title = title;
        this.desc = desc;
        this.fileurl = fileurl;
        this.percentoff = percentoff;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getPercentoff() {
        return percentoff;
    }

    public void setPercentoff(String percentoff) {
        this.percentoff = percentoff;
    }
}
