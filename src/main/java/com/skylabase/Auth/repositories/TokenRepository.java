package com.skylabase.Auth.repositories;

import com.skylabase.Auth.models.AccessToken;
import com.skylabase.model.User;

public interface TokenRepository {

    AccessToken createAccessToken(User user);

    AccessToken findByAccessToken(String stringToken);

    boolean contains(String stringToken);

    boolean contains(User user);

    void delete(String stringToken);

    void deleteAll();
}
