package com.koreatech.naeilro.network.entity.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class EnrollObject {
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;

    public EnrollObject(int success, int status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
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
