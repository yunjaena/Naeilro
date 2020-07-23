package com.koreatech.naeilro.network.entity.tour;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class TourInfo {
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
    @Element(name = "title")
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RestaurantInfo{" +
                "address='" + address + '\'' +
                ", areaCode=" + areaCode +
                ", categoryOne='" + categoryOne + '\'' +
                ", categoryTwo='" + categoryTwo + '\'' +
                ", categoryThree='" + categoryThree + '\'' +
                ", contentID=" + contentID +
                ", contentTypeID=" + contentTypeID +
                ", createdTime='" + createdTime + '\'' +
                ", firstImage='" + firstImage + '\'' +
                ", secondImage='" + secondImage + '\'' +
                ", mapX=" + mapX +
                ", mapY=" + mapY +
                ", level=" + level +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", readCount=" + readCount +
                ", sigunguCode=" + sigunguCode +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}