package com.example.newsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.logic.network.NewsNetwork;
import com.example.newsdemo.logic.network.model.NewsInfo;
import com.example.newsdemo.logic.network.model.NewsResponse;
import com.example.newsdemo.logic.room.entity.News;
import com.example.newsdemo.ui.utils.NewsUtils;

import java.util.List;

public class TestActivity extends AppCompatActivity implements NewsCallBack {
    private final String TAG = "TestActivity";

//    private NewsResponse newsResponse;
    private MutableLiveData<NewsResponse> newsResponseMutableLiveData = new MutableLiveData<>();
    private boolean isInsertDatabase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button getNewsBtn = findViewById(R.id.getNewsBtn);
        Button insertNewsBtn = findViewById(R.id.insertNewsBtn);
        Button queryNewsBtn = findViewById(R.id.queryNewsBtn);
        Button insertInternetBtn = findViewById(R.id.insertInternetNewsBtn);

        getNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository.getInstance().searchNews("top", TestActivity.this);
            }
        });
        insertNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news = new News();
                news.setTitle("test1234");
                Repository.getInstance().insertNews(news);
            }
        });
        queryNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<News> newsList = Repository.getInstance().queryAllNews();
                if (newsList != null) {
                    for (News news: newsList) {
                        Log.d(TAG, "Title->" + news.getTitle());
                    }
                }
            }
        });
        insertInternetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository.getInstance().searchNews("top", TestActivity.this);
                isInsertDatabase = true;
            }
        });

        newsResponseMutableLiveData.observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                if (newsResponse != null) {
                    for (NewsInfo newsInfo : newsResponse.getResult().getData()) {
                        Log.d(TAG, newsInfo.getTitle());
                    }
                    if (isInsertDatabase) {
                        List<NewsInfo> newsInfoList = newsResponse.getResult().getData();
                        List<News> newsList = NewsUtils.getInstance().newsInfoToNews(newsInfoList);
                        Repository.getInstance().insertAllNews(newsList);
                    }
                }
            }
        });

        Repository.getInstance().deleteAllNews();
    }

    @Override
    public void callback() {
        newsResponseMutableLiveData.setValue(NewsNetwork.getInstance().getNowNewsRespone());
//        if (newsResponse != null) {
//            for (News news: newsResponse.getResult().getData()) {
//                Log.d(TAG, news.getTitle());
//            }
//        }
    }
}