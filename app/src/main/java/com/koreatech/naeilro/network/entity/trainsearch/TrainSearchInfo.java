package com.koreatech.naeilro.network.entity.trainsearch;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class TrainSearchInfo {
    @Element(name = "adultcharge")
    private int adultCharge;
    @Element(name = "arrplacename")
    private String arrivalPlaceName;
    @Element(name = "arrplandtime")
    private String arrivalTime;
    @Element(name = "depplacename")
    private String departurePlaceName;
    @Element(name = "depplandtime")
    private String departureTime;
    @Element(name = "traingradename")
    private String trainName;
    @Element(name = "trainno")
    private String trainNumber;

    public int getAdultCharge() {
        return adultCharge;
    }

    public void setAdultCharge(int adultCharge) {
        this.adultCharge = adultCharge;
    }

    public String getArrivalPlaceName() {
        return arrivalPlaceName;
    }

    public void setArrivalPlaceName(String arrivalPlaceName) {
        this.arrivalPlaceName = arrivalPlaceName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDeparturePlaceName() {
        return departurePlaceName;
    }

    public void setDeparturePlaceName(String departurePlaceName) {
        this.departurePlaceName = departurePlaceName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Override
    public String toString() {
        return "TrainSearchInfo{" +
                "adultCharge=" + adultCharge +
                ", arrivalPlaceName='" + arrivalPlaceName + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", departurePlaceName='" + departurePlaceName + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", trainName='" + trainName + '\'' +
                ", trainNumber='" + trainNumber + '\'' +
                '}';
    }
}