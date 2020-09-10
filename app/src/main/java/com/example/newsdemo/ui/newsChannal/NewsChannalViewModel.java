package com.example.newsdemo.ui.newsChannal;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.newsdemo.NewsCallBack;
import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.logic.room.entity.News;
import com.example.newsdemo.logic.room.entity.NewsChannal;

import java.util.ArrayList;
import java.util.List;

public class NewsChannalViewModel extends ViewModel {
    private final String TAG = "NewsChannalViewModel";

    private List<NewsChannal> newsChannalList = new ArrayList<>();
    private MutableLiveData<Boolean> newsChannalLiveData = new MutableLiveData<>();

    public NewsChannalViewModel() {
        super();
        List<NewsChannal> newsChannalList2 = Repository.getInstance().queryAllNewsChannals();
        newsChannalList.clear();
        newsChannalList.addAll(newsChannalList2);
    }

    public LiveData getNewsChannalLiveData = Transformations.switchMap(newsChannalLiveData, b->{
        List<NewsChannal> newsChannalList2 = Repository.getInstance().queryAllNewsChannals();
        boolean flag = false;
        Log.d(TAG, "---TEST");
        if (newsChannalList2 != null) {
            newsChannalList.clear();
            newsChannalList.addAll(newsChannalList2);
            flag = true;
        }
        return new MutableLiveData<>(flag);
    });

    public void getNewsChannalData() {
        newsChannalLiveData.setValue(newsChannalLiveData.getValue());
    }

    public List<NewsChannal> getNewsChannalList() {
        return newsChannalList;
    }

    public void deleteNewsChannalByChannalName(String channalName) {
        Repository.getInstance().deleteNewsChannalByChannalName(channalName);
    }

    public void insertNewsChannal(String newsChannalName) {
        NewsChannal newsChannal = new NewsChannal();
        newsChannal.setChannalName(newsChannalName);
        Repository.getInstance().insertNewsChannal(newsChannal);
    }

}
