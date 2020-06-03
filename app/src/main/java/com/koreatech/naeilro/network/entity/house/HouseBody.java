package com.koreatech.naeilro.network.entity.house;

import com.koreatech.naeilro.network.entity.traininfo.TrainInfoItem;
import com.koreatech.naeilro.network.entity.trainsearch.TrainSearchInfoItem;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class HouseBody {
    @ElementList(inline = true, required = false)
    private List<HouseInfoItems> houseInfoItemList;
    @Element(name = "numOfRows")
    private int numOfRows;
    @Element(name = "pageNo")
    private int pageNo;
    @Element(name = "totalCount")
    private int totalCount;

    public List<HouseInfoItems> getHouseInfoItemList() {
        return houseInfoItemList;
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
