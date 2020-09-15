package com.koreatech.naeilro.network.entity.tour;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class TourInfoItem {
    @ElementList(inline = true, required = false)
    private List<TourInfo> tourInfoList;

    public List<TourInfo> getTourInfoList() {
        return tourInfoList;
    }
}
