package com.example.newsdemo.logic.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News {
    @PrimaryKey(autoGenerate = true)
    private long id = 0;
    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String url;
    @ColumnInfo(name = "thumbnail_pic_s")
    private String picUrl;
    @ColumnInfo(name = "is_delete")
    private boolean isDelete;
    @ColumnInfo(name = "is_collect")
    private boolean isCollect;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

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

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setIsCollect(boolean collect) { isCollect = collect; }

    public boolean getIsCollect() {return isCollect;};

    public void setIsDelete(boolean delete) { isDelete = delete; }

    public boolean getIsDelete() {return isDelete;}
}
