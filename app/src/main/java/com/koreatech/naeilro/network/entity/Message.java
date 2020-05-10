package com.koreatech.naeilro.network.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "header")
public class Message {

    @Element(name = "resultCode")
    public String resultCode;
    @Element(name = "resultMsg")
    public String resultMsg;

    @Override
    public String toString() {
        return "TrainArrivalDepartInfo{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }

}