package com.koreatech.naeilro.network.entity.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
@Root(name = "item", strict = false)
public class Festival {


    @Element(name = "addr1", required = false)
    private String addr1;

    @Element(name = "areacode", required = false)
    private int areacode;

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

    @Element(name = "firstimage", required = false)
    private String firstimage;

    @Element(name = "firstimage2", required = false)
    private String firstimage2;


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

    @Element(name = "eventstartdate", required = false)
    private String eventstartdate;

    @Element(name = "eventenddate", required = false)
    private String eventenddate;
    @Element(name = "overview", required = false)
    private String overview;
    @Element(name = "agelimit", required = false)
    private String agelimit;
    @Element(name = "discountinfofestival", required = false)
    private String discountinfofestival;
    @Element(name = "playtime", required = false)
    private String playtime;
    @Element(name = "spendtimefestival", required = false)
    private String spendtimefestival;
    @Element(name = "usetimefestival", required = false)
    private String usetimefestival;
    @Element(name = "eventplace", required = false)
    private String eventplace;


    public String getAddr1() {
        return addr1;
    }

    public int getAreacode() {
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

    public String getEventstartdate() {
        return eventstartdate;
    }

    public String getEventenddate() {
        return eventenddate;
    }

    public String getOverview() {
        return overview;
    }

    public String getAgelimit() {
        return agelimit;
    }

    public String getDiscountinfofestival() {
        return discountinfofestival;
    }

    public String getPlaytime() {
        return playtime;
    }

    public String getSpendtimefestival() {
        return spendtimefestival;
    }

    public String getUsetimefestival() {
        return usetimefestival;
    }

    public String getEventplace() {
        return eventplace;
    }
}
