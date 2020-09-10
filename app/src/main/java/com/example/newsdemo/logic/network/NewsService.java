package com.example.newsdemo.logic.network;

import com.example.newsdemo.logic.network.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("index?key=654381b330478ddfe6b1fdc15aa8f22c")
    public Call<NewsResponse> getAppData(@Query("type") String type);
}
