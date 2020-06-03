package com.koreatech.naeilro.network.entity.house;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class HouseInfo {
    @Element(name = "addr1", required = false)
    private String addr1;
    @Element(name = "addr2", required = false)
    private String addr2;
    @Element(name = "areacode", required = false)
    private String areacode;
    @Element(name = "cat1", required = false)
    private String cat1;
    @Element(name = "cat2", required = false)
    private String cat2;
    @Element(name = "cat3", required = false)
    private String cat3;
    @Element(name = "contentid", required = false)
    private String contentid;
    @Element(name = "contenttypeid", required = false)
    private String contenttypeid;
    @Element(name = "createdtime", required = false)
    private String createdtime;
    @Element(name = "mapx", required = false)
    private String mapx;
    @Element(name = "mapy", required = false)
    private String mapy;
    @Element(name = "mlevel", required = false)
    private String mlevel;
    @Element(name = "modifiedtime", required = false)
    private String modifiedtime;
    @Element(name = "readcount", required = false)
    private String readcount;
    @Element(name = "sigungucode", required = false)
    private String sigungucode;
    @Element(name = "tel", required = false)
    private String tel;
    @Element(name = "title", required = false)
    private String title;
    @Element(name = "firstimage", required = false)
    private String firstimage;
    @Element(name = "hanok", required = false)
    private int hanok;

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public String getAreacode() {
        return areacode;
    }

    public String getCat1() {
        return cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public String getContentid() {
        return contentid;
    }

    public String getContenttypeid() {
        return contenttypeid;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public String getMapx() {
        return mapx;
    }

    public String getMapy() {
        return mapy;
    }

    public String getMlevel() {
        return mlevel;
    }

    public String getModifiedtime() {
        return modifiedtime;
    }

    public String getReadcount() {
        return readcount;
    }

    public String getSigungucode() {
        return sigungucode;
    }

    public String getTel() {
        return tel;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public int getHanok() {
        return hanok;
    }
}
