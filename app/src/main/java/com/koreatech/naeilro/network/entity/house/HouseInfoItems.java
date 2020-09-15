package com.koreatech.naeilro.network.entity.house;

import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;
@Root(name = "items")
public class HouseInfoItems {
    @ElementList(inline = true, required = false)
    private List<HouseInfo> houseInfoList;

    public List<HouseInfo> getHouseInfoList() {
        return houseInfoList;
    }
}
