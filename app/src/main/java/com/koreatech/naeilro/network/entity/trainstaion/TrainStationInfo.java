package com.koreatech.naeilro.network.entity.trainstaion;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class TrainStationInfo {
    @Element(name = "nodeid")
    private String stationCode;
    @Element(name = "nodename")
    private String stationName;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "TrainSearchInfo{" +
                "stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}