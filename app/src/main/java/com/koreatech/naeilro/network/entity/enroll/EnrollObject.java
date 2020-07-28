package com.koreatech.naeilro.network.entity.enroll;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class EnrollObject {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("pw")
    private String pw;

    public EnrollObject(String name, String email, String pw) {
        this.name = name;
        this.email = email;
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }
}
