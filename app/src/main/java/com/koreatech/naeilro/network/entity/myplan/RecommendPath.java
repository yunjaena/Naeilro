package com.koreatech.naeilro.network.entity.myplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecommendPath {
    @SerializedName("station_no")
    @Expose
    private int stationNumber;
    @SerializedName("name")
    @Expose
    private String stationName;
    @SerializedName("areacode")
    @Expose
    private int areaCode;
    @SerializedName("mapX")
    @Expose
    private float mapX;
    @SerializedName("mapY")
    @Expose
    private float mapY;
    @SerializedName("distance")
    @Expose
    private double distance;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("department")
    @Expose
    private boolean department;
    @SerializedName("arrive_node_no")
    @Expose
    private boolean arriveNodeNumber;
    @SerializedName("node_no")
    @Expose
    private int nodeNumber;


    public int getAreaCode() {
        return areaCode;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public float getMapX() {
        return mapX;
    }

    public float getMapY() {
        return mapY;
    }

    public double getDistance() {
        return distance;
    }

    public String getPlace() {
        return place;
    }

    public boolean isDepartment() {
        return department;
    }

    public boolean isArriveNodeNumber() {
        return arriveNodeNumber;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }
}
