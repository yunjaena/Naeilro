package com.koreatech.naeilro.network.entity.trainsearch;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class TrainSearchInfoItem {
    @ElementList(inline = true, required = false)
    private List<TrainSearchInfo> trainSearchInfoList;

    public List<TrainSearchInfo> getTrainSearchInfoList() {
        return trainSearchInfoList;
    }

    public void setTrainSearchInfoList(List<TrainSearchInfo> trainSearchInfoList) {
        this.trainSearchInfoList = trainSearchInfoList;
    }

    @Override
    public String toString() {
        return "TrainSearchInfoItem{" +
                "trainSearchInfoList=" + trainSearchInfoList +
                '}';
    }
}
