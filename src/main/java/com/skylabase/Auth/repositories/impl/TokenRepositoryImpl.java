package com.skylabase.Auth.repositories.impl;

import com.skylabase.Auth.models.AccessToken;
import com.skylabase.Auth.repositories.TokenRepository;
import com.skylabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private static List<AccessToken> accessTokens;

    private String digestString(String data) {
        return String.valueOf("amp" + data.hashCode());
    }

    @Override
    public AccessToken createAccessToken(User user) {
        String stringToken = digestString(user.getUsername() + user.hashCode()); // TODO: add password field here.
        AccessToken accessToken = new AccessToken(user, stringToken);
        if (!contains(user)) {
            accessTokens.remove(findByAccessToken(stringToken));
        }
        accessTokens.add(accessToken);
        return accessToken;
    }

    @Override
    public AccessToken findByAccessToken(String stringToken) {
        for (AccessToken accessToken : accessTokens) {
            if (accessToken.getStringToken().equals(stringToken)) {
                return accessToken;
            }
        }
        return null;
    }

    @Override
    public boolean contains(String stringToken) {
        for (AccessToken accessToken : accessTokens) {
            if (accessToken.getStringToken().equals(stringToken)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        for (AccessToken accessToken : accessTokens) {
            if (accessToken.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(String stringToken) {
        if (contains(stringToken)) {
            accessTokens.remove(findByAccessToken(stringToken));
        }
    }

    @Override
    public void deleteAll() {
        accessTokens = new ArrayList<>();
    }
}