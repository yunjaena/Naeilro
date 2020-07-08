package com.koreatech.naeilro.network.entity.trainsearch;

import com.koreatech.naeilro.network.entity.Message;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class TrainSearchList {

    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<TrainSearchInfoBody> trainSearchInfoBodyList;

    private String cityCode;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }


    public List<TrainSearchInfoBody> getTrainSearchInfoBodyList() {
        return trainSearchInfoBodyList;
    }

    public void setTrainSearchInfoBodyList(List<TrainSearchInfoBody> trainSearchInfoBodyList) {
        this.trainSearchInfoBodyList = trainSearchInfoBodyList;
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
                ", trainInfoList=" + trainSearchInfoBodyList +
                '}';
    }
}
