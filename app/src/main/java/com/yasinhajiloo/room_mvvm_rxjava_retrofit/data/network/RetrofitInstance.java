package com.yasinhajiloo.room_mvvm_rxjava_retrofit.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitInstance {
    private static Retrofit mInstance;
    private static final String baseUrl = "https://api.unsplash.com";

    public static Retrofit getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitInstance.class) {
                if (mInstance == null) {
                    mInstance = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return mInstance;
    }

}
