package com.koreatech.naeilro.network.entity.traininfo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class TrainInfoItem {
    @ElementList(inline = true, required = false)
    private List<TrainInfo> trainInfoBodyList;

    public List<TrainInfo> getTrainInfoBodyList() {
        return trainInfoBodyList;
    }

    public void setTrainInfoBodyList(List<TrainInfo> trainInfoBodyList) {
        this.trainInfoBodyList = trainInfoBodyList;
    }

    @Override
    public String toString() {
        return "TrainInfoItem{" +
                "trainInfoBodyList=" + trainInfoBodyList +
                '}';
    }
}
