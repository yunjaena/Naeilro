package com.koreatech.naeilro.network.entity.house;

import com.koreatech.naeilro.network.entity.Message;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfoBody;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class HouseInfoList {
    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<HouseBody> HouseBodyList;


    public List<Message> getMessageList() {
        return messageList;
    }

    public List<HouseBody> getHouseBodyList() {
        return HouseBodyList;
    }


}
