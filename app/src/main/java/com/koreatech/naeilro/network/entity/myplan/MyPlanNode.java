package com.koreatech.naeilro.network.entity.myplan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPlanNode {
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

    public MyPlanNode(String contentType, String contendID, String title, String thumbnail) {
        this.contentType = contentType;
        this.contendID = contendID;
        this.title = title;
        this.thumbnail = thumbnail;
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
}
