package com.koreatech.naeilro.network.entity.restaurant;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class RestaurantInfoBody {

    @ElementList(inline = true, required = false)
    private List<RestaurantInfoItem> restaurantInfoItemList;
    @Element(name = "numOfRows")
    private int numOfRows;
    @Element(name = "pageNo")
    private int pageNo;
    @Element(name = "totalCount")
    private int totalCount;

    public List<RestaurantInfoItem> getRestaurantInfoItemList() {
        return restaurantInfoItemList;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}