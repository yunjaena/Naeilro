package com.koreatech.naeilro.network.entity.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
