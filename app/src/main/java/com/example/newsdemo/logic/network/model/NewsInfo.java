package com.example.newsdemo.logic.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsInfo {
    private String uniquekey;
    private String title;
    private String date;
    private String category;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    private String url;
    @SerializedName("thumbnail_pic_s")
    @Expose
    private String picUrl;
    @SerializedName("thumbnail_pic_s02")
    @Expose
    private String picUrl2;
    @SerializedName("thumbnail_pic_s03")
    @Expose
    private String picUrl3;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl2(String picUrl2) {
        this.picUrl2 = picUrl2;
    }

    public String getPicUrl2() {
        return picUrl2;
    }

    public void setPicUrl3(String picUrl3) {
        this.picUrl3 = picUrl3;
    }

    public String getPicUrl3() {
        return picUrl3;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
