package com.koreatech.naeilro.network.entity.traininfo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class TrainInfoBody {

    @ElementList(inline = true, required = false)
    private List<TrainInfoItem> trainInfoItemList;


    public List<TrainInfoItem> getTrainInfoItemList() {
        return trainInfoItemList;
    }

    public void setTrainInfoItemList(List<TrainInfoItem> trainInfoItemList) {
        this.trainInfoItemList = trainInfoItemList;
    }

    @Override
    public String toString() {
        return "TrainSearchInfoBody{" +
                "trainInfoItemList=" + trainInfoItemList +
                '}';
    }
}
