package com.koreatech.naeilro.network.entity.search;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class SearchInfoItem {
    @ElementList(inline = true, required = false)
    private List<SearchInfo> searchInfoList;

    public List<SearchInfo> getSearchInfoList() {
        return searchInfoList;
    }

    public void setSearchInfoList(List<SearchInfo> searchInfoList) {
        this.searchInfoList = searchInfoList;
    }
}
