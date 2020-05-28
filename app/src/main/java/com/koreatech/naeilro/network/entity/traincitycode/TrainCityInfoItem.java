package com.koreatech.naeilro.network.entity.traincitycode;

import com.koreatech.naeilro.network.entity.traininfo.TrainInfo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class TrainCityInfoItem {
    @ElementList(inline = true, required = false)
    private List<TrainCityInfo> trainCityInfoList;

    public List<TrainCityInfo> getTrainCityInfoList() {
        return trainCityInfoList;
    }

    public void setTrainCityInfoList(List<TrainCityInfo> trainCityInfoList) {
        this.trainCityInfoList = trainCityInfoList;
    }

    @Override
    public String toString() {
        return "TrainSearchInfoItem{" +
                "trainCityInfoList=" + trainCityInfoList +
                '}';
    }
}
