package com.example.newsdemo.logic.room.dao;

import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newsdemo.logic.room.entity.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    public Long insertNews(News news);

    @Insert
    public void insertAllNews(List<News> newsList);

    @Update
    public void updateNews(News news);

    @Query("SELECT * FROM news")
    public List<News> queryAllNews();
    @Query("SELECT * FROM news WHERE category = :type")
    public List<News> queryAllNewsByCategory(String type);
    @Query("SELECT * FROM news WHERE uniquekey = :uniquekey")
    public News queryNewsByUniquekey(String uniquekey);
    @Query("SELECT * FROM news WHERE title like :title")
    public List<News> queryAllNewsByTitle(String title);
    @Query("SELECT * FROM news WHERE is_collect = :isCollect")
    public List<News> queryAllNewsByIsCollect(boolean isCollect);

    @Query("DELETE FROM news")
    public void deleteAllNews();

}
