package com.myapplication.util.application;

import android.content.Context;

public class Application extends android.app.Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Application.context;
    }

    public static Context getContext() {
        return context;
    }
}
