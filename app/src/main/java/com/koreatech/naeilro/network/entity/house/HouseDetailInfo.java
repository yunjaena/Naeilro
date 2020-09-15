package com.koreatech.naeilro.network.entity.house;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class HouseDetailInfo {
    @Element(name = "addr1", required = false)
    private String addr1;
    @Element(name = "addr2", required = false)
    private String addr2;
    @Element(name = "areacode", required = false)
    private String areacode;
    @Element(name = "contentid", required = false)
    private String contentid;
    @Element(name = "contenttypeid", required = false)
    private String contenttypeid;
    @Element(name = "firstimage", required = false)
    private String firstimage;
    @Element(name = "firstimage2", required = false)
    private String firstimage2;
    @Element(name = "mapx", required = false)
    private String mapx;
    @Element(name = "mapy", required = false)
    private String mapy;
    @Element(name = "overview", required = false)
    private String overview;
    @Element(name = "sigungucode", required = false)
    private String sigungucode;

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public String getAreacode() {
        return areacode;
    }

    public String getContentid() {
        return contentid;
    }

    public String getContenttypeid() {
        return contenttypeid;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public String getMapx() {
        return mapx;
    }

    public String getMapy() {
        return mapy;
    }

    public String getOverview() {
        return overview;
    }

    public String getSigungucode() {
        return sigungucode;
    }
}
