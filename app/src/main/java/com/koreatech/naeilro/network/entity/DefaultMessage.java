package com.koreatech.naeilro.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultMessage {
    @SerializedName("success")
    @Expose
    private boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
