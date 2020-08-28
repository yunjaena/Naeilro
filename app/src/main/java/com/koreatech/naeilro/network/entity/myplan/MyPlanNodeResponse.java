package com.koreatech.naeilro.network.entity.myplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPlanNodeResponse {
    @SerializedName("success")
    @Expose
    private boolean isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("items")
    @Expose
    private List<MyPlanNode> myPlanNodeList;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public List<MyPlanNode> getMyPlanNodeList() {
        return myPlanNodeList;
    }
}
