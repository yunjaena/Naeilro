package com.koreatech.naeilro.network.entity.myplan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPlanNode implements Parcelable {
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("content_id")
    @Expose
    private String contendID;
    @SerializedName("node_no")
    @Expose
    private String nodeNumber;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("mapX")
    @Expose
    private Float mapX;
    @SerializedName("mapY")
    @Expose
    private Float mapY;
    @SerializedName("areacode")
    @Expose
    private String areaCode;

    public MyPlanNode(String contentType, String contendID, String title, String thumbnail, Float mapX, Float mapY, String areaCode) {
        this.contentType = contentType;
        this.contendID = contendID;
        this.title = title;
        this.thumbnail = thumbnail;
        this.mapX = mapX;
        this.mapY = mapY;
        this.areaCode = areaCode;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContendID() {
        return contendID;
    }

    public String getNodeNumber() {
        return nodeNumber;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public Float getMapX() {
        return mapX;
    }

    public Float getMapY() {
        return mapY;
    }

    public String getAreaCode() {
        return areaCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private MyPlanNode(Parcel in) {
        contentType = in.readString();
        contendID =in.readString();
        nodeNumber = in.readString();
        title = in.readString();
        thumbnail = in.readString();
        mapX = in.readFloat();
        mapY = in.readFloat();
        areaCode = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contentType);
        dest.writeString(contendID);
        dest.writeString(nodeNumber);
        dest.writeString(title);
        dest.writeString(thumbnail);
        dest.writeFloat(mapX);
        dest.writeFloat(mapY);
        dest.writeString(areaCode);

    }

    public static final Parcelable.Creator<MyPlanNode> CREATOR = new Parcelable.Creator<MyPlanNode>() {
        public MyPlanNode createFromParcel(Parcel in) {
            return new MyPlanNode(in);
        }

        public MyPlanNode[] newArray(int size) {
            return new MyPlanNode[size];

        }
    };
}
