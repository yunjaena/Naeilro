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
    @Element(name = "overview", required = false)
    private String overview;

    @Element(name = "barbecue", required = false)
    private String barbecue;
    @Element(name = "beauty", required = false)
    private String beauty;
    @Element(name = "benikia", required = false)
    private String benikia;
    @Element(name = "beverage", required = false)
    private String beverage;
    @Element(name = "bicycle", required = false)
    private String bicycle;
    @Element(name = "campfire", required = false)
    private String campfire;
    @Element(name = "checkintime", required = false)
    private String checkintime;
    @Element(name = "checkouttime", required = false)
    private String checkouttime;
    @Element(name = "chkcooking", required = false)
    private String chkcooking;
    @Element(name = "fitness", required = false)
    private String fitness;
    @Element(name = "karaoke", required = false)
    private String karaoke;
    @Element(name = "parkinglodging", required = false)
    private String parkinglodging;
    @Element(name = "pickup", required = false)
    private String pickup;
    @Element(name = "publicpc", required = false)
    private String publicpc;
    @Element(name = "roomtype", required = false)
    private String roomtype;
    @Element(name = "seminar", required = false)
    private String seminar;
    @Element(name = "sports", required = false)
    private String sports;
    @Element(name = "subfacility", required = false)            //부대시설
    private String subfacility;
    @Element(name = "foodplace", required = false)
    private String foodplace;
    @Element(name = "reservationlodging", required = false)
    private String reservationlodging;                      //예약안내
    @Element(name = "accomcountlodging", required = false)
    private String accomcountlodging;                       //수용인원
    @Element(name = "infoname", required = false)
    private String infoname;
    @Element(name = "infotext", required = false)
    private String infotext;
    @Element(name = "originimgurl", required = false)
    private String originimgurl;
    @Element(name = "smallimageurl", required = false)
    private String smallimageurl;

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

    public String getBarbecue() {
        return barbecue;
    }

    public String getBeauty() {
        return beauty;
    }

    public String getBenikia() {
        return benikia;
    }

    public String getBeverage() {
        return beverage;
    }

    public String getBicycle() {
        return bicycle;
    }

    public String getCampfire() {
        return campfire;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public String getCheckouttime() {
        return checkouttime;
    }

    public String getChkcooking() {
        return chkcooking;
    }

    public String getFitness() {
        return fitness;
    }

    public String getKaraoke() {
        return karaoke;
    }

    public String getParkinglodging() {
        return parkinglodging;
    }

    public String getPickup() {
        return pickup;
    }

    public String getPublicpc() {
        return publicpc;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public String getSeminar() {
        return seminar;
    }

    public String getSports() {
        return sports;
    }

    public String getSubfacility() {
        return subfacility;
    }

    public String getFoodplace() {
        return foodplace;
    }

    public String getReservationlodging() {
        return reservationlodging;
    }

    public String getAccomcountlodging() {
        return accomcountlodging;
    }
}
