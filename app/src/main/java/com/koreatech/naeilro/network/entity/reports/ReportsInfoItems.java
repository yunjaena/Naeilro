package com.koreatech.naeilro.network.entity.reports;

import com.koreatech.naeilro.network.entity.house.HouseInfo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class ReportsInfoItems {
    @ElementList(inline = true, required = false)
    private List<Reports> reportsList;

    public List<Reports> getReportsList() {
        return reportsList;
    }
}
