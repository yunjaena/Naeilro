package com.koreatech.naeilro.network.entity.traininfo;

import com.koreatech.naeilro.network.entity.Message;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class TrainList {

    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<TrainInfoBody> trainInfoItem;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<TrainInfoBody> getTrainInfoItem() {
        return trainInfoItem;
    }

    public void setTrainInfoItem(List<TrainInfoBody> trainInfoItem) {
        this.trainInfoItem = trainInfoItem;
    }

    @Override
    public String toString() {
        return "TrainList{" +
                "messageList=" + messageList +
                ", trainInfoList=" + trainInfoItem +
                '}';
    }
}
