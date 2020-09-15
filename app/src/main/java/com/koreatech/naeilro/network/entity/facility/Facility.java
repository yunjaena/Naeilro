package com.koreatech.naeilro.network.entity.facility;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Facility {
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
    @Element(name = "mapx", required = false)
    private String mapx;
    @Element(name = "mapy", required = false)
    private String mapy;
    @Element(name = "sigungucode", required = false)
    private String sigungucode;
    @Element(name = "tel", required = false)
    private String tel;
    @Element(name = "title", required = false)
    private String title;
    @Element(name = "overview", required = false)
    private String overview;

    @Element(name = "infoname", required = false)
    private String infoname;
    @Element(name = "infotext", required = false)
    private String infotext;
    @Element(name = "originimgurl", required = false)
    private String originimgurl;
    @Element(name = "smallimageurl", required = false)
    private String smallimageurl;

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

    public String getMapx() {
        return mapx;
    }

    public String getMapy() {
        return mapy;
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

    public String getOverview() {
        return overview;
    }

    public String getInfoname() {
        return infoname;
    }

    public String getInfotext() {
        return infotext;
    }

    public String getOriginimgurl() {
        return originimgurl;
    }

    public String getSmallimageurl() {
        return smallimageurl;
    }
}
