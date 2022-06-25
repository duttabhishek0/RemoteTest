package com.abhishek.remotetest

import android.app.Application
import com.abhishek.remotetest.domain.StoreDatabase

public class RemoteTestApp : Application() {
    private var appDatabase: StoreDatabase? = null

    override fun onCreate() {
        super.onCreate()
        initializeDb()
    }

    private fun initializeDb() {
        appDatabase = StoreDatabase.Companion.getInstance(applicationContext)
    }

    fun getAppDatabase(): StoreDatabase? {
        return appDatabase
    }
}
