package com.skylabase.Auth.models;

import com.skylabase.model.User;

public class AccessToken {
    private User user;
    private String stringToken;

    public AccessToken(User user, String stringToken) {
        this.user = user;
        this.stringToken = stringToken;
    }

    public User getUser() {
        return user;
    }

    public String getStringToken() {
        return stringToken;
    }
}
