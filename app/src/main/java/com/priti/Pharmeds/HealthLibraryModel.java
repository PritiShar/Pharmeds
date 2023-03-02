package com.priti.Pharmeds;

public class HealthLibraryModel {
    String title , description , fileurl ;

    public HealthLibraryModel() {
    }

    public HealthLibraryModel(String title, String description, String fileurl) {
        this.title = title;
        this.description = description;
        this.fileurl = fileurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
