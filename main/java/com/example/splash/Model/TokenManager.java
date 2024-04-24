package com.example.splash.Model;

public class TokenManager {
    String id,date;
    int currentToken;

    public TokenManager() {
    }

    public TokenManager(String id, String date, int currentToken) {
        this.id = id;
        this.date = date;
        this.currentToken = currentToken;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getCurrentToken() {
        return currentToken;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCurrentToken(int currentToken) {
        this.currentToken = currentToken;
    }
}
