package com.koreatech.naeilro.network.entity.reports;

import com.koreatech.naeilro.network.entity.Message;
import com.koreatech.naeilro.network.entity.house.HouseBody;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class ReportsInfoList {
    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<ReportsBody> reportsBodyList;


    public List<Message> getMessageList() {
        return messageList;
    }

    public List<ReportsBody> getReportsBodyList() {
        return reportsBodyList;
    }
}
