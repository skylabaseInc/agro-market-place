package com.skylabase.Auth.services.impl;

import com.skylabase.Auth.models.AccessToken;
import com.skylabase.Auth.repositories.TokenRepository;
import com.skylabase.Auth.services.AuthenticationService;
import com.skylabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;

class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private static TokenRepository tokenRepository;

    @Override
    public AccessToken login(User user) {
        return tokenRepository.createAccessToken(user);
    }

    @Override
    public AccessToken getAccessToken(String stringToken) {
        return tokenRepository.findByAccessToken(stringToken);
    }

    @Override
    public boolean isStringTokenValid(String stringToken) {
        return tokenRepository.contains(stringToken);
    }

    @Override
    public void removeAccessToken(String stringToken) {
        tokenRepository.delete(stringToken);
    }

    @Override
    public void clearAccessTokens() {
        tokenRepository.deleteAll();
    }
}
