package com.example.newsdemo.logic.network;

import android.util.Log;

import com.example.newsdemo.NewsCallBack;
import com.example.newsdemo.logic.network.model.NewsInfo;
import com.example.newsdemo.logic.network.model.NewsResponse;
import com.example.newsdemo.logic.network.model.NewsResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsNetwork {
    private final String TAG = "NewsNetwork";
    private static NewsNetwork instance;

    private NewsResponse nowNewsRespone;
    private NewsCallBack[] nowNewsCallbacks;

    private NewsService newsService = ServiceCreator.getInstance().create(NewsService.class);

    private NewsNetwork(){}

    public static NewsNetwork getInstance(){
        if (instance == null) {
            instance = new NewsNetwork();
        }
        return instance;
    }

    public void searchNews(String type, NewsCallBack... newsCallBack) {
        nowNewsCallbacks = newsCallBack;
        newsService.getAppData(type).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                nowNewsRespone = response.body();
                NewsResult newsResult = response.body().getResult();
                if (newsResult == null) {
                    return;
                }
                Log.d(TAG, "---SUCCESS---");
                for (NewsInfo newsInfo : newsResult.getData()) {
                    Log.d(TAG, newsInfo.getTitle());
                }

                if (nowNewsCallbacks != null) {
                    for (NewsCallBack newsCallBack: nowNewsCallbacks) {
                        newsCallBack.callback();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d(TAG, "---FAIL---");
                nowNewsRespone.setReason("失败");
                t.printStackTrace();
            }
        });
    }

    public NewsResponse getNowNewsRespone() {
        return nowNewsRespone;
    }
}
