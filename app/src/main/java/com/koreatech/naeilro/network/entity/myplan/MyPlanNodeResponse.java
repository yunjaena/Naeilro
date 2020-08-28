package com.koreatech.naeilro.network.entity.myplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPlanNodeResponse {
    @SerializedName("success")
    @Expose
    private int isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("items")
    @Expose
    private List<MyPlanNode> myPlanNodeList;

    public int getIsSuccess() {
        return isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess == 1;
    }

    public String getMessage() {
        return message;
    }

    public List<MyPlanNode> getMyPlanNodeList() {
        return myPlanNodeList;
    }
}
