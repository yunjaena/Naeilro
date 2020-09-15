package com.koreatech.naeilro.network.entity.house;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class HouseDetailInfoItems {
    @ElementList(inline = true, required = false)
    private List<HouseDetailInfo> houseDetailInfoList;

    public List<HouseDetailInfo> getHouseDetailInfoList() {
        return houseDetailInfoList;
    }
}
