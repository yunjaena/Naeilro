package com.koreatech.naeilro.network.entity.tour;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class TourInfo {
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

    @Element(name = "accomcount", required = false)
    private String personLimitCount;
    @Element(name = "chkbabycarriage", required = false)
    private String babyCarriageInvalidate;
    @Element(name = "chkcreditcard", required = false)
    private String creditCardInvalidate;
    @Element(name = "chkpet", required = false)
    private String petInvaildate;
    @Element(name = "expagerange", required = false)
    private String experienceAgeRange;
    @Element(name = "expguide", required = false)
    private String experienceGuide;
    @Element(name = "infocenter", required = false)
    private String infoCenterPhoneNumber;
    @Element(name = "parking", required = false)
    private String parkingInvaildate;
    @Element(name = "restdate", required = false)
    private String restDate;
    @Element(name = "usetime", required = false)
    private String runningTime;


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

    public String getOverview() {
        return overview;
    }

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

    public String getPersonLimitCount() {
        return personLimitCount;
    }

    public String getBabyCarriageInvalidate() {
        return babyCarriageInvalidate;
    }

    public String getCreditCardInvalidate() {
        return creditCardInvalidate;
    }

    public String getPetInvaildate() {
        return petInvaildate;
    }

    public String getExperienceAgeRange() {
        return experienceAgeRange;
    }

    public String getExperienceGuide() {
        return experienceGuide;
    }

    public String getInfoCenterPhoneNumber() {
        return infoCenterPhoneNumber;
    }

    public String getParkingInvaildate() {
        return parkingInvaildate;
    }

    public String getRestDate() {
        return restDate;
    }

    public String getRunningTime() {
        return runningTime;
    }
}