package com.click2eat.app;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitZomato {

    public static ZomatoApi getApi() {
        return getRetrofit().create(ZomatoApi.class);
    }

    private static retrofit2.Retrofit getRetrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl("https://developers.zomato.com/api/v2.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
