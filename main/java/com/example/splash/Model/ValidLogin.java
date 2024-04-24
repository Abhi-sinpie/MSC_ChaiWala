package com.example.splash.Model;

public class ValidLogin {
    String username,password;

    public ValidLogin() {
    }

    public ValidLogin(String username, String password) {
        username = username;
        password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
