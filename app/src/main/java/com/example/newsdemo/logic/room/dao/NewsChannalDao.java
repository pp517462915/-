package com.example.newsdemo.logic.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.newsdemo.logic.room.entity.NewsChannal;

import java.util.List;

@Dao
public interface NewsChannalDao {

    @Insert
    public long insertNewsChannal(NewsChannal newsChannal);

    @Query("SELECT * FROM news_channal WHERE channal_name = :channalName")
    public NewsChannal queryNewsChannalByChannalName(String channalName);

    @Query("SELECT * FROM news_channal")
    public List<NewsChannal> queryAllNewsChannals();

    @Query("DELETE FROM news_channal WHERE channal_name = :channalName")
    public void deleteNewsChannalByChannalName(String channalName);
}
