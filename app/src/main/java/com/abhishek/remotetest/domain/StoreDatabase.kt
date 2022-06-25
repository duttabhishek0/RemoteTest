package com.abhishek.remotetest.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abhishek.remotetest.domain.dao.StoreDao
import com.abhishek.remotetest.domain.entity.Store

@Database(entities = [Store::class], version = 1, exportSchema = false)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun storeDao(): StoreDao

    companion object {

        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getInstance(context: Context): StoreDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, StoreDatabase::class.java, "bottle_rocket.db"
            ).build()
    }
}
