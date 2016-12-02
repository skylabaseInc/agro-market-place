package com.skylabase.Auth.services;

import com.skylabase.model.User;

public interface AuthenticationService {

    String createAccessToken(User user);

    String getAccessToken(String stringToken);

    boolean isStringTokenValid(String accessToken);

    void removeAccessToken(String accessToken);

    void clearAccessTokens();
}
