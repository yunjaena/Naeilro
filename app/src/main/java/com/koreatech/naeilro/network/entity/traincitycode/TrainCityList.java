package com.koreatech.naeilro.network.entity.traincitycode;

import com.koreatech.naeilro.network.entity.Message;
import com.koreatech.naeilro.network.entity.traininfo.TrainInfoBody;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class TrainCityList {

    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<TrainCityInfoBody> trainCityInfoBodyList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }


    public List<TrainCityInfoBody> getTrainCityInfoBodyList() {
        return trainCityInfoBodyList;
    }

    public void setTrainCityInfoBodyList(List<TrainCityInfoBody> trainCityInfoBodyList) {
        this.trainCityInfoBodyList = trainCityInfoBodyList;
    }

    @Override
    public String toString() {
        return "TrainCityList{" +
                "messageList=" + messageList +
                ", trainInfoList=" + trainCityInfoBodyList +
                '}';
    }
}
