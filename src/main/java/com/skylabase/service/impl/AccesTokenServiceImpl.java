package com.skylabase.service.impl;

import com.skylabase.model.User;
import com.skylabase.service.AccesTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccesTokenServiceImpl implements AccesTokenService {

    private class AccessToken {
        private User user;
        private String stringToken;
        public AccessToken(User user, String stringToken){
            this.user = user;
            this.stringToken = stringToken;
        }
        public User getUser(){return this.user;}
        public String getAccessToken(){return this.stringToken;}

    }

    @Autowired
    private static List<String> accessTokens;

    @Override
    public String digestString(String data) throws Exception {
        return String.valueOf("amp" + data.hashCode());
    }

    @Override
    public String getAccessToken(User user) throws Exception {
        String accessToken = digestString(user.getUsername()); // TODO: add password field to User model
        if(accessTokens.contains(accessToken)) {
            accessTokens.remove(accessToken);
        }
        accessTokens.add(accessToken);
        return accessToken;
    }

    @Override
    public boolean isAccessTokenValid(String accessToken) throws Exception {
        return accessTokens.contains(accessToken);
    }

    @Override
    public void removeAccessToken(String accessToken) throws Exception {
        if (isAccessTokenValid(accessToken)) {
            accessTokens.remove(accessToken);
        }
    }

    @Override
    public void clearAccessTokens() {
        accessTokens = new ArrayList<>();
    }
}