package com.example.ferreteriaapp;

import android.app.Application;

import androidx.multidex.MultiDex;

import timber.log.Timber;

public class FerreteriaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
