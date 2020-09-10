package com.example.newsdemo.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 单例类
 */
public class ServiceCreator {
    private static ServiceCreator instance;
    private final String BASE_URL = "http://v.juhe.cn/toutiao/";
    private Retrofit retrofit;

    private ServiceCreator() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServiceCreator getInstance() {
        if (instance == null) {
            instance = new ServiceCreator();
        }
        return  instance;
    }

    public <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
