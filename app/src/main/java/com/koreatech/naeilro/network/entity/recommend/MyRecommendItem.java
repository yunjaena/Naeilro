package com.koreatech.naeilro.network.entity.recommend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyRecommendItem {
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("content_id")
    @Expose
    private String contendID;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public MyRecommendItem(String contentType, String contendID, String title, String thumbnail) {
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

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
