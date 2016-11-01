package com.example.user.clientapp;

/**
 * Created by User on 31/10/2016.
 */

public class Color {

    private String name;
    private String hex;


    public Color(String name, String hex) {
        this.hex = hex;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}