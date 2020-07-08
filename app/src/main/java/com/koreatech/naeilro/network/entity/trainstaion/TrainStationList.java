package com.koreatech.naeilro.network.entity.trainstaion;

import com.koreatech.naeilro.network.entity.Message;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class TrainStationList {

    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<TrainStationInfoBody> trainStationInfoBodyList;

    private String cityCode;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }


    public List<TrainStationInfoBody> getTrainStationInfoBodyList() {
        return trainStationInfoBodyList;
    }

    public void setTrainStationInfoBodyList(List<TrainStationInfoBody> trainStationInfoBodyList) {
        this.trainStationInfoBodyList = trainStationInfoBodyList;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "RestaurantList{" +
                "messageList=" + messageList +
                ", trainInfoList=" + trainStationInfoBodyList +
                '}';
    }
}
