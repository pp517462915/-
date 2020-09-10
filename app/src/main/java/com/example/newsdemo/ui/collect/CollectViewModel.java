package com.example.newsdemo.ui.collect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.logic.room.entity.News;

import java.util.ArrayList;
import java.util.List;

public class CollectViewModel extends ViewModel {

    List<News> newsList = new ArrayList<>();

    MutableLiveData<Boolean> liveData = new MutableLiveData<>();

    LiveData<Boolean> newsListLiveData = Transformations.switchMap(liveData, b->{
        boolean flag = false;

        // 获得收藏
        List<News> newsList2 = Repository.getInstance().queryAllNewsByIsCollect(true);
        if (newsList2 != null) {
            flag = true;
            newsList.clear();
            for (News news: newsList2) {
                if (news.getIsDelete()) {
                    continue;
                }
                newsList.add(news);
            }
        }
        return new MutableLiveData<>(flag);
    });


    public void setLiveData() {
        liveData.setValue(liveData.getValue());
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
