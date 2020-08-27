package com.koreatech.naeilro.auth;

import java.util.Date;

public interface JWTToken {
    void saveRefreshToken(String token);

    void saveAccessToken(String token);

    void updateToken();

    String getAccessToken();

    String getRefreshToken();

    Date getRefreshTokenExpiredDate();

    Date getAccessTokenExpiredDate();

    boolean isAccessTokenExpired();

    boolean isRefreshTokenExpired();

    void refreshTokenExpiredAction();

    void deleteAuth();
}
