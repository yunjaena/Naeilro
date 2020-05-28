package com.koreatech.naeilro.network.entity.traincitycode;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class TrainCityInfo {
    @Element(name = "citycode")
    private String cityCode;
    @Element(name = "cityname")
    private String cityName;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "TrainSearchInfo{" +
                "cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}