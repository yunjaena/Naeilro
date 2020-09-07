package com.koreatech.naeilro.network.entity.restaurant;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class RestaurantInfo {
    @Element(name = "addr1", required = false)
    private String address;
    @Element(name = "areacode", required = false)
    private int areaCode;
    @Element(name = "cat1", required = false)
    private String categoryOne;
    @Element(name = "cat2", required = false)
    private String categoryTwo;
    @Element(name = "cat3", required = false)
    private String categoryThree;
    @Element(name = "contentid", required = false)
    private int contentID;
    @Element(name = "contenttypeid", required = false)
    private int contentTypeID;
    @Element(name = "createdtime", required = false)
    private String createdTime;
    @Element(name = "firstimage" ,required = false)
    private String firstImage;
    @Element(name = "firstimage2", required = false)
    private String secondImage;
    @Element(name = "mapx", required = false)
    private double mapX;
    @Element(name = "mapy", required = false)
    private double mapY;
    @Element(name = "mlevel", required = false)
    private int level;
    @Element(name = "modifiedtime", required = false)
    private String modifiedTime;
    @Element(name = "readcount", required = false)
    private int readCount;
    @Element(name = "sigungucode", required = false)
    private int sigunguCode;
    @Element(name = "tel", required = false)
    private String telephoneNumber;
    @Element(name = "title",required = false)
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

    @Element(name = "firstmenu", required = false)
    private String firstmenu;
    @Element(name = "opentimefood", required = false)
    private String opentimefood;
    @Element(name = "parkingfood", required = false)
    private String parkingfood;
    @Element(name = "restdatefood", required = false)
    private String restdatefood;
    @Element(name = "treatmenu", required = false)
    private String treatmenu;

    public String getFirstmenu() {
        return firstmenu;
    }

    public String getOpentimefood() {
        return opentimefood;
    }

    public String getParkingfood() {
        return parkingfood;
    }

    public String getRestdatefood() {
        return restdatefood;
    }

    public String getTreatmenu() {
        return treatmenu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(String categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    public String getCategoryThree() {
        return categoryThree;
    }

    public void setCategoryThree(String categoryThree) {
        this.categoryThree = categoryThree;
    }

    public int getContentID() {
        return contentID;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public int getContentTypeID() {
        return contentTypeID;
    }

    public void setContentTypeID(int contentTypeID) {
        this.contentTypeID = contentTypeID;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getSecondImage() {
        return secondImage;
    }

    public void setSecondImage(String secondImage) {
        this.secondImage = secondImage;
    }

    public double getMapX() {
        return mapX;
    }

    public void setMapX(double mapX) {
        this.mapX = mapX;
    }

    public double getMapY() {
        return mapY;
    }

    public void setMapY(double mapY) {
        this.mapY = mapY;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getSigunguCode() {
        return sigunguCode;
    }

    public void setSigunguCode(int sigunguCode) {
        this.sigunguCode = sigunguCode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}