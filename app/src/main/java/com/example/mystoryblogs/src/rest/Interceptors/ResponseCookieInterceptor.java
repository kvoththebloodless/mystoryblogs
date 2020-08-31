package com.example.mystoryblogs.src.rest.Interceptors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mystoryblogs.src.MyStoryBlogsApplication;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

// from https://medium.com/@dileepsattawan/how-to-handle-cookies-with-retrofit-in-android-8b1812178949
//Modified by Gourav Acharya
public class ResponseCookieInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            List<String> headers = originalResponse.headers("Set-Cookie");
            SharedPreferences.Editor cookieEditor = PreferenceManager.getDefaultSharedPreferences(MyStoryBlogsApplication.get()).edit();
            HashSet<String> cookies=new HashSet<>();
            for (String cookie : headers
            ) {
                cookies.add(cookie);

            }
            cookieEditor.putStringSet("cookies",cookies);
            cookieEditor.commit();


        }

        return originalResponse;
    }
}
