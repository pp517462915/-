package com.example.newsdemo.logic.network.model;

import com.example.newsdemo.logic.network.model.NewsResult;

public class NewsResponse {
    private String reason;
    private NewsResult result;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setResult(NewsResult result) {
        this.result = result;
    }

    public NewsResult getResult() {
        return result;
    }
}

