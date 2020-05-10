package com.koreatech.naeilro.network.entity;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class TrainArrivalDepartInfo {

    @ElementList(inline = true, required = false)
    public List<Message> messageList;

}
