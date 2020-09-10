package com.example.newsdemo.logic.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 是现在拥有的通道
@Entity(tableName = "news_channal")
public class NewsChannal {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "channal_name")
    private String channalName;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setChannalName(String channalName) {
        this.channalName = channalName;
    }

    public String getChannalName() {
        return channalName;
    }
}
