package com.skylabase.service;

import com.skylabase.model.User;

public interface AccesTokenService {

    String digestString(String data) throws Exception;
    String getAccessToken(User user) throws Exception;
    boolean isAccessTokenValid(String accessToken) throws Exception;
    void removeAccessToken(String accessToken) throws Exception;
    void clearAccessTokens();
}
