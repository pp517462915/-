package com.example.newsdemo.logic;


import androidx.room.Insert;
import androidx.room.Query;

import com.example.newsdemo.NewsCallBack;
import com.example.newsdemo.logic.network.NewsNetwork;
import com.example.newsdemo.logic.room.dao.NewsChannalDao;
import com.example.newsdemo.logic.room.dao.NewsDao;
import com.example.newsdemo.logic.room.database.NewsDatabase;
import com.example.newsdemo.logic.room.entity.News;
import com.example.newsdemo.logic.room.entity.NewsChannal;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository instance;

    private NewsNetwork newsNetwork = NewsNetwork.getInstance();

    private NewsDao newsDao;
    private NewsChannalDao newsChannalDao;

    private Repository() {
        newsDao = NewsDatabase.getDatabase().newsDao();
        newsChannalDao = NewsDatabase.getDatabase().newsChannalDao();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    // News
    public void searchNews(String type, NewsCallBack... newsCallBacks) {
        newsNetwork.searchNews(type, newsCallBacks);
    }
    public Long insertNews(News news) {
        return newsDao.insertNews(news);
    }
    public void insertAllNews(List<News> newsList) {
        List<News> newsList2 = new ArrayList<>();
        for (News news: newsList) {
            // 数据库中有就不插入
            if (newsDao.queryNewsByUniquekey(news.getUniquekey()) != null) {
                continue;
            }
            newsList2.add(news);
        }
        newsDao.insertAllNews(newsList2);
    }
    public List<News> queryAllNews() {
        return newsDao.queryAllNews();
    }
    public void deleteAllNews() {
        newsDao.deleteAllNews();
    }
    public List<News> queryAllNewsByCategory(String type) { return newsDao.queryAllNewsByCategory(type); }
    public List<News> queryAllNewsByTitle(String title) {
        title = "%" + title + "%";
        return newsDao.queryAllNewsByTitle(title);
    }
    public void updateNews(News news) { newsDao.updateNews(news);}
    public List<News> queryAllNewsByIsCollect(boolean isCollect) {return newsDao.queryAllNewsByIsCollect(isCollect);}

    // newsChannal
    public long insertNewsChannal(NewsChannal newsChannal) {
        if (newsChannalDao.queryNewsChannalByChannalName(newsChannal.getChannalName()) == null) {
            return newsChannalDao.insertNewsChannal(newsChannal);
        }
        return -1;
    }
    public List<NewsChannal> queryAllNewsChannals() {
        return newsChannalDao.queryAllNewsChannals();
    }
    public void deleteNewsChannalByChannalName(String channalName) {
        newsChannalDao.deleteNewsChannalByChannalName(channalName);
    }

}
