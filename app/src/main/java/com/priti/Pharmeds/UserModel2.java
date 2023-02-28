package com.priti.Pharmeds;

public class UserModel2 {
     String name,email,password,image;

    public UserModel2(String name, String email, String password, String image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserModel2() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
