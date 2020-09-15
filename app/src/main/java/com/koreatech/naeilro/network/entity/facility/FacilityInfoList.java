package com.koreatech.naeilro.network.entity.facility;

import com.koreatech.naeilro.network.entity.Message;
import com.koreatech.naeilro.network.entity.reports.ReportsBody;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class FacilityInfoList {
    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<FacilityBody> facilityBodyList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public List<FacilityBody> getFacilityBodyList() {
        return facilityBodyList;
    }
}
