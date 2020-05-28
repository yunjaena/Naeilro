package com.koreatech.naeilro.network.entity.trainstaion;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class TrainStationInfoItem {
    @ElementList(inline = true, required = false)
    private List<TrainStationInfo> trainStationInfoList;

    public List<TrainStationInfo> getTrainStationInfoList() {
        return trainStationInfoList;
    }

    public void setTrainStationInfoList(List<TrainStationInfo> trainStationInfoList) {
        this.trainStationInfoList = trainStationInfoList;
    }

    @Override
    public String toString() {
        return "TrainStationInfoItem{" +
                "trainStationInfoList=" + trainStationInfoList +
                '}';
    }
}
