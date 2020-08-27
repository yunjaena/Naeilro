package com.koreatech.naeilro.network.entity.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public UserInfo(int success, int status, String message, User user) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public int getSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

