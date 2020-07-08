package com.koreatech.naeilro.network.entity.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreatech.naeilro.network.entity.house.HouseInfoItems;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;
@Root(name = "body")
public class FestivalBody {
    public List<FestivalInfoItems> getFestivalInfoItems() {
        return festivalInfoItems;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    @ElementList(inline = true, required = false)
    private List<FestivalInfoItems> festivalInfoItems;
    @Element(name = "numOfRows")
    private int numOfRows;
    @Element(name = "pageNo")
    private int pageNo;
    @Element(name = "totalCount")
    private int totalCount;

}
