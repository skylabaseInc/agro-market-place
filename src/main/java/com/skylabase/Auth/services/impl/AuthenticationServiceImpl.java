package com.skylabase.Auth.services.impl;

import com.skylabase.Auth.models.AccessToken;
import com.skylabase.Auth.services.AuthenticationService;
import com.skylabase.model.User;
import com.skylabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserService userService;

    private String digestString(String data) {
        return String.valueOf("amp" + data.hashCode());
    }

    @Override
    public String createAccessToken(User user) {
        String id = user.getId();
        if (id != null) {
            user = userService.findById(id);
        } else {
            return null;
        }
        if (user != null) {
            // TODO: add password here
            String tokenString = digestString(user.getUsername() + user.getEmail());
            AccessToken accessToken = new AccessToken(user, tokenString);
            tokenRepository.save(accessToken);
            return accessToken.getStringToken();
        } else {
            return null;
        }
    }

    @Override
    public String getAccessToken(String stringToken) {
        AccessToken accessToken = tokenRepository.findByStringToken(stringToken);
        return accessToken.getStringToken();
    }

    @Override
    public boolean isStringTokenValid(String stringToken) {
        return tokenRepository.exists(stringToken);
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

interface TokenRepository extends MongoRepository<AccessToken, String> {

    AccessToken findByStringToken(String stringToken);
}