package com.koreatech.naeilro.network.entity.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SignIn {
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;

    public int getSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
