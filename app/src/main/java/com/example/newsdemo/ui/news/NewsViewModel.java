package com.example.newsdemo.ui.news;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.newsdemo.NewsCallBack;
import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.logic.room.entity.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel implements NewsCallBack {

    private final String TAG = "NewsViewmodel";

    private List<News> newsList = new ArrayList<>();
    private MutableLiveData<String> searchTypeLiveData = new MutableLiveData<>();

    private LiveData newsListLiveData = Transformations.switchMap(searchTypeLiveData,
        type-> {
            List<News> newsList2 = Repository.getInstance().queryAllNewsByCategory(type);
            Boolean value = false;
            if (newsList2 != null) {
                value = true;
            }
            newsList.clear();
            for (News news: newsList2) {
                if (!news.getIsDelete()) {
                    newsList.add(news);
                }
            }

            return new MutableLiveData(value);
        });

    public List<News> getNewsList() {
        return newsList;
    }

    public void searchNewsType (String type) {
        this.searchTypeLiveData.setValue(type);
    }

    public LiveData getNewsListLiveData() {
        return newsListLiveData;
    }

    @Override
    public void callback() {
        searchNewsType(searchTypeLiveData.getValue());
    }
}
