package com.skylabase.Auth.services;

import com.skylabase.Auth.models.AccessToken;
import com.skylabase.model.User;

public interface AuthenticationService {

    AccessToken login(User user);

    AccessToken getAccessToken(String stringToken) throws Exception;

    boolean isStringTokenValid(String accessToken) throws Exception;

    void removeAccessToken(String accessToken) throws Exception;

    void clearAccessTokens();
}
