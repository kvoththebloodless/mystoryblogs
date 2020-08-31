package com.example.mystoryblogs.src.rest;

import android.content.Context;

import com.example.mystoryblogs.src.rest.Interceptors.RequestCookieInterceptor;
import com.example.mystoryblogs.src.rest.Interceptors.ResponseCookieInterceptor;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://mystoryblogs.herokuapp.com";

    public static Retrofit getRetrofitInstance() {

        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new RequestCookieInterceptor());
        builder.addInterceptor(new ResponseCookieInterceptor());
        client = builder.build();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
