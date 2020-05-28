package com.koreatech.naeilro.network.entity.traininfo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class TrainInfo {
    @Element(name = "vehiclekndid")
    private String vehicleId;
    @Element(name = "vehiclekndnm")
    private String vehicleName;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    @Override
    public String toString() {
        return "TrainStationInfo{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                '}';
    }
}
