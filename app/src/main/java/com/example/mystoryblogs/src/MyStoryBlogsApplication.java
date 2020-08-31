package com.example.mystoryblogs.src;

import android.app.Application;

public class MyStoryBlogsApplication extends Application {
    private static MyStoryBlogsApplication instance;
    public static MyStoryBlogsApplication get() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
