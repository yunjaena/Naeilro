package com.koreatech.naeilro.network.entity.facility;

import com.koreatech.naeilro.network.entity.reports.Reports;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "items")
public class FacilityInfoItems {
    @ElementList(inline = true, required = false)
    private List<Facility> facilityList;

    public List<Facility> getFacilityList() {
        return facilityList;
    }
}
