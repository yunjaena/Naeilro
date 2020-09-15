package com.koreatech.naeilro.network.entity.search;

import com.koreatech.naeilro.network.entity.Message;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class SearchList {

    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<SearchInfoBody>  searchInfoInfoBodyList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<SearchInfoBody> getSearchInfoInfoBodyList() {
        return searchInfoInfoBodyList;
    }

    public void setSearchInfoInfoBodyList(List<SearchInfoBody> searchInfoInfoBodyList) {
        this.searchInfoInfoBodyList = searchInfoInfoBodyList;
    }
}
