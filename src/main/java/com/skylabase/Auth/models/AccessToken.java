package com.skylabase.Auth.models;

import com.skylabase.model.User;
import org.springframework.data.annotation.Id;

public class AccessToken {

    @Id
    private String stringToken;
    private User user;

    public AccessToken() {
    }

    public AccessToken(User user, String stringToken) {
        this.user = user;
        this.stringToken = stringToken;
    }

    public String getStringToken() {
        return stringToken;
    }

    public User getUser() {
        return user;
    }
}
