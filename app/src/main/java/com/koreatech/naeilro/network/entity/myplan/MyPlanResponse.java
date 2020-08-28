package com.koreatech.naeilro.network.entity.myplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPlanResponse {
    @SerializedName("success")
    @Expose
    private boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("plans")
    @Expose
    private List<MyPlan> myPlanList;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public List<MyPlan> getMyPlanList() {
        return myPlanList;
    }
}
