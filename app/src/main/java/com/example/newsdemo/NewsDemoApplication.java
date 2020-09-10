package com.example.newsdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.newsdemo.logic.Repository;
import com.example.newsdemo.logic.network.NewsNetwork;
import com.example.newsdemo.logic.network.model.NewsInfo;
import com.example.newsdemo.logic.room.entity.News;
import com.example.newsdemo.logic.room.entity.NewsChannal;
import com.example.newsdemo.ui.utils.NewsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDemoApplication extends Application {

    private static Context context;
    private final String TAG = "NewsDemoApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

//        Repository.getInstance().deleteAllNews();

        NewsUtils.getInstance();
    }

    public static Context getContext(){
        return context;
    }

}
