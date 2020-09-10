package com.example.newsdemo.logic.network.model;

import java.util.List;

public class NewsResult {
    private String stat;
    private List<NewsInfo> data;


    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getStat() {
        return stat;
    }

    public void setData(List<NewsInfo> data) {
        this.data = data;
    }

    public List<NewsInfo> getData() {
        return data;
    }
}
