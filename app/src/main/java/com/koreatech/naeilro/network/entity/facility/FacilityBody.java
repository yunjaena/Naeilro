package com.koreatech.naeilro.network.entity.facility;

import com.koreatech.naeilro.network.entity.reports.ReportsInfoItems;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class FacilityBody {
    @ElementList(inline = true, required = false)
    private List<FacilityInfoItems> facilityInfoItems;
    @Element(name = "numOfRows")
    private int numOfRows;
    @Element(name = "pageNo")
    private int pageNo;
    @Element(name = "totalCount")
    private int totalCount;

    public List<FacilityInfoItems> getFacilityInfoItems() {
        return facilityInfoItems;
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
