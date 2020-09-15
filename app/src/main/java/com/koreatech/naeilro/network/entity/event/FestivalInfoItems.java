package com.koreatech.naeilro.network.entity.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreatech.naeilro.network.entity.house.HouseInfo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;
@Root(name = "items")
public class FestivalInfoItems {
    @ElementList(inline = true, required = false)
    private List<Festival> festivalList;

    public List<Festival> getFestivalList() {
        return festivalList;
    }
}
