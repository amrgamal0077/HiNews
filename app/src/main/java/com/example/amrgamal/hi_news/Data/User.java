package com.example.amrgamal.hi_news.Data;

/**
 * Created by AmrGamal on 28/04/2019.
 */

public class User {
    String name,pass,country;
    public User(){}
    public User (String name,String pass,String country){
        this.country = country;
        this.name = name;
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPass() {
        return pass;
    }
}
