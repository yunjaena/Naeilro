package com.koreatech.naeilro.network.entity.restaurant;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class RestaurantInfoBody {

    @ElementList(inline = true, required = false)
    private List<RestaurantInfoItem> restaurantInfoItemList;
    @Element(name = "numOfRows", required = false)
    private int numOfRows;
    @Element(name = "pageNo", required = false)
    private int pageNo;
    @Element(name = "totalCount", required = false)
    private int totalCount;

    public List<RestaurantInfoItem> getRestaurantInfoItemList() {
        return restaurantInfoItemList;
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

}