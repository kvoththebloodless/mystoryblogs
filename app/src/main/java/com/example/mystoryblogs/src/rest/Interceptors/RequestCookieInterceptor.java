package com.example.mystoryblogs.src.rest.Interceptors;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

// from https://medium.com/@dileepsattawan/how-to-handle-cookies-with-retrofit-in-android-8b1812178949
//Modified by :Gourav Acharya
public class RequestCookieInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";
    private Context context;

    public RequestCookieInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> preferences = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet("cookies", new HashSet<String>());

        Request original = chain.request();

        String pattern = "Path=/([^;]*)";
            for (String cookie : preferences) {
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(cookie);
                boolean matched=m.find();
                if(matched&&original.url().toString().contains(m.group(0).split("=")[1]))
                    builder.addHeader("Cookie", cookie);
                Log.d("match",m.group().toString());
            }


        return chain.proceed(builder.build());
    }
}
