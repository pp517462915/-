package com.example.newsdemo.ui.search;

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

public class SearchViewModel extends ViewModel implements NewsCallBack {
    private final String TAG = "SearchViewModel";
    private List<News> newsList = new ArrayList<>();

    MutableLiveData<String> searchContent = new MutableLiveData<>();

    LiveData<Boolean> newsListLiveData = Transformations.switchMap(searchContent, content -> {
        boolean flag = false;
        List<News> newsList2 = Repository.getInstance().queryAllNewsByTitle(content);
        if (newsList2 != null) {
            flag = true;
            newsList.clear();
            for (News news: newsList2) {
                if (!news.getIsDelete()) {
                    newsList.add(news);
                }
            }
        }
        LiveData<Boolean> liveData = new MutableLiveData(flag);
        return liveData;
    });



    public void setSearchContent(String searchContent) {
        this.searchContent.setValue(searchContent);
    }

    public String getSearchContent() {
        return searchContent.getValue();
    }

    @Override
    public void callback() {
//        setSearchContent(this.searchContent)
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
