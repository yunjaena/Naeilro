package com.koreatech.naeilro.network.entity.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreatech.naeilro.network.entity.Message;
import com.koreatech.naeilro.network.entity.house.HouseBody;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;
@Root(name = "response", strict = false)
public class FestivalInfoList {
    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<FestivalBody> festivalBodyList;


    public List<Message> getMessageList() {
        return messageList;
    }

    public List<FestivalBody> getFestivalBodyList() {
        return festivalBodyList;
    }
}
