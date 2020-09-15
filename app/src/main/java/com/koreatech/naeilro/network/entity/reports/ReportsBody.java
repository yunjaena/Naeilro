package com.koreatech.naeilro.network.entity.reports;

import com.koreatech.naeilro.network.entity.house.HouseInfoItems;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class ReportsBody {
    @ElementList(inline = true, required = false)
    private List<ReportsInfoItems> reportsInfoItems;
    @Element(name = "numOfRows")
    private int numOfRows;
    @Element(name = "pageNo")
    private int pageNo;
    @Element(name = "totalCount")
    private int totalCount;

    public List<ReportsInfoItems> getReportsInfoItems() {
        return reportsInfoItems;
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
