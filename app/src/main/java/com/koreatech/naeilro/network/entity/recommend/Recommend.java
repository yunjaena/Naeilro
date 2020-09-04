package com.koreatech.naeilro.network.entity.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreatech.naeilro.network.entity.myplan.MyPlanNode;

import java.util.List;

public class Recommend {
    @SerializedName("success")
    @Expose
    private int isSuccess;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("items")
    @Expose
    private List<MyRecommendItem> myRecommendItemList;

    public int getIsSuccess() {
        return isSuccess;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<MyRecommendItem> getMyRecommendItemList() {
        return myRecommendItemList;
    }
    public boolean isSuccess(){return isSuccess == 1;}
}
