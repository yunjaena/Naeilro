package com.koreatech.naeilro.network.entity.restaurant;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class RestaurantInfoItem {
    @ElementList(inline = true, required = false)
    private List<RestaurantInfo> restaurantInfoList;

    public List<RestaurantInfo> getRestaurantInfoList() {
        return restaurantInfoList;
    }

    public void setRestaurantInfoList(List<RestaurantInfo> restaurantInfoList) {
        this.restaurantInfoList = restaurantInfoList;
    }
}
