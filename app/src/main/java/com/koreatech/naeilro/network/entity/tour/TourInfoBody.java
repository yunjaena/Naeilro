package com.koreatech.naeilro.network.entity.tour;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "body")
public class TourInfoBody {

    @ElementList(inline = true, required = false)
    private List<TourInfoItem> tourInfoItemList;
    @Element(name = "numOfRows")
    private int numOfRows;
    @Element(name = "pageNo")
    private int pageNo;
    @Element(name = "totalCount")
    private int totalCount;

    public List<TourInfoItem> getTourInfoItemList() {
        return tourInfoItemList;
    }

    public void setTourInfoItemList(List<TourInfoItem> tourInfoItemList) {
        this.tourInfoItemList = tourInfoItemList;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}