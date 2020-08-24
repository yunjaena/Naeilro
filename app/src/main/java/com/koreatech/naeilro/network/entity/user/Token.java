package com.koreatech.naeilro.network.entity.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Token {
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("access")
    @Expose
    private String accessToken;
    @SerializedName("refresh")
    @Expose
    private String refreshToken;
    @SerializedName("message")
    @Expose
    private String message;

    public int getSuccess() {
        return success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMessage() {
        return message;
    }
}
