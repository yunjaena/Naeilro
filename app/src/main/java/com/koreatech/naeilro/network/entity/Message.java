package com.koreatech.naeilro.network.entity;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "header")
public class Message {

    @Element(name = "resultCode")
    private String resultCode;
    @Element(name = "resultMsg")
    private String resultMsg;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    @Override
    public String toString() {
        return "TrainArrivalDepartInfo{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }

}