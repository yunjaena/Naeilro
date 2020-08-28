package com.koreatech.naeilro.network.entity.myplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPlan {
    @SerializedName("plan_title")
    @Expose
    private String planTitle;
    @SerializedName("plan_no")
    @Expose
    private String planNumber;

    public String getPlanTitle() {
        return planTitle;
    }

    public String getPlanNumber() {
        return planNumber;
    }
}
