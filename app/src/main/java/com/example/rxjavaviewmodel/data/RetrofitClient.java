package com.example.rxjavaviewmodel.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getClient() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.17:3000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }


    private RetrofitClient() {
    }
}
