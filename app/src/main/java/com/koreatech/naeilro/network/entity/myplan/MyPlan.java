package com.koreatech.naeilro.network.entity.myplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPlan {
    @SerializedName("plan_title")
    @Expose
    private String planTitle;
    @SerializedName("plan_no")
    @Expose
    private String planNumber;

    private List<MyPlanNode> myPlanNodeList;

    private boolean isContainPlan;

    public MyPlan(String planTitle, String planNumber) {
        this.planTitle = planTitle;
        this.planNumber = planNumber;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public boolean isContainPlan() {
        return isContainPlan;
    }

    public void setContainPlan(boolean containPlan) {
        isContainPlan = containPlan;
    }

    public void setMyPlanNodeList(List<MyPlanNode> myPlanNodeList) {
        this.myPlanNodeList = myPlanNodeList;
    }

    public List<MyPlanNode> getMyPlanNodeList() {
        return myPlanNodeList;
    }
}
