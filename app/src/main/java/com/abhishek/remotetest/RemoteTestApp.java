package com.abhishek.remotetest;

import android.app.Application;

import com.abhishek.remotetest.domain.StoreDatabase;

public class RemoteTestApp extends Application {

    private static StoreDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDb();
    }

    private void initializeDb() {
        appDatabase = StoreDatabase.Companion.getInstance(getApplicationContext());
    }

    public static StoreDatabase getAppDatabase() {
        return appDatabase;
    }
}