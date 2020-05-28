package com.koreatech.naeilro.network.entity.traincitycode;

import com.koreatech.naeilro.network.entity.traininfo.TrainInfoItem;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class TrainCityInfoBody {

    @ElementList(inline = true, required = false)
    private List<TrainCityInfoItem> trainCityInfoItemList;

    public List<TrainCityInfoItem> getTrainCityInfoItemList() {
        return trainCityInfoItemList;
    }

    public void setTrainCityInfoItemList(List<TrainCityInfoItem> trainCityInfoItemList) {
        this.trainCityInfoItemList = trainCityInfoItemList;
    }

    @Override
    public String toString() {
        return "TrainStationInfoBody{" +
                "trainCityInfoItemList=" + trainCityInfoItemList +
                '}';
    }
}