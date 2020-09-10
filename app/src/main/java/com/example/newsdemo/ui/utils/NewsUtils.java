package com.example.newsdemo.ui.utils;

import android.util.Log;

import com.example.newsdemo.NewsCallBack;
import com.example.newsdemo.R;
import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.logic.network.NewsNetwork;
import com.example.newsdemo.logic.network.model.NewsInfo;
import com.example.newsdemo.logic.room.entity.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsUtils implements NewsCallBack{
    private static NewsUtils instance;;

    private final String TAG = "NewsUtils";

    // type的数据库名称转换
    private static Map<String, String> typeOfRoomToInternet = new HashMap<>();
    // 新闻频道名和图片对应关系
    private static Map<String, Integer> channalNameToPic = new HashMap<>();

    private NewsUtils() {
        typeOfRoomToInternet.put("头条", "top");
        typeOfRoomToInternet.put("社会", "shehui");
        typeOfRoomToInternet.put("国内", "guonei");
        typeOfRoomToInternet.put("国际", "guoji");
        typeOfRoomToInternet.put("娱乐", "yule");
        typeOfRoomToInternet.put("体育", "tiyu");
        typeOfRoomToInternet.put("军事", "junshi");
        typeOfRoomToInternet.put("科技", "keji");
        typeOfRoomToInternet.put("财经", "caijing");
        typeOfRoomToInternet.put("时尚", "shishang");

        channalNameToPic.put("头条", R.drawable.top_bg);
        channalNameToPic.put("社会", R.drawable.shehui_bg);
        channalNameToPic.put("国内", R.drawable.guonei_bg);
        channalNameToPic.put("国际", R.drawable.guoji_bg);
        channalNameToPic.put("娱乐", R.drawable.yule_bg);
        channalNameToPic.put("体育", R.drawable.tiyu_bg);
        channalNameToPic.put("军事", R.drawable.junshi_bg);
        channalNameToPic.put("科技", R.drawable.keji_bg);
        channalNameToPic.put("财经", R.drawable.caijing_bg);
        channalNameToPic.put("时尚", R.drawable.shishang_bg);

        searchNewsToDB(typeOfRoomToInternet.get(typeOfRoomToInternet.get("头条")), this);
    }

    public void searchNewsToDB(String type, NewsCallBack newsCallBack) {
        if (newsCallBack instanceof NewsUtils) {
            Repository.getInstance().searchNews(type, newsCallBack);
            Log.d(TAG, "---TEST2---");
        } else {
            Repository.getInstance().searchNews(type, this, newsCallBack);
            Log.d(TAG, "---TEST3---");
        }
    }

    public static NewsUtils getInstance() {
        if (instance == null) {
            instance = new NewsUtils();
        }
        return instance;
    }

    // 将对象NewsInfo转换到News
    public List<News> newsInfoToNews(List<NewsInfo> newsInfoList) {
        List<News> newsList = new ArrayList<>();

        for (NewsInfo newsInfo: newsInfoList) {
            News news = new News();
            if (newsInfo.getUrl().equals("")) {
                continue;
            }
            news.setUrl(newsInfo.getUrl());
            news.setTitle(newsInfo.getTitle());
            news.setCategory(newsInfo.getCategory());
            news.setDate(newsInfo.getDate());
            news.setPicUrl(newsInfo.getPicUrl());
            news.setUniquekey(newsInfo.getUniquekey());
            newsList.add(news);
        }

        return newsList;
    }

    @Override
    public void callback() {
        List<News> newsList = newsInfoToNews(NewsNetwork.getInstance().getNowNewsRespone().getResult().getData());
        Repository.getInstance().insertAllNews(newsList);
        Log.d(TAG, "---TEST4---");
    }

    public static Map<String, String> getTypeOfRoomToInternet() {
        return typeOfRoomToInternet;
    }

    public static Map<String, Integer> getChannalNameToPic() {
        return channalNameToPic;
    }
}
