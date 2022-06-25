package com.abhishek.remotetest.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abhishek.remotetest.domain.entity.Store

@Dao
interface StoreDao {

    @Query("SELECT * FROM store")
    fun getAll(): List<Store>

    @Query("SELECT * FROM store WHERE storeID IN (:storeID)")
    fun storeByIds(storeID: String): Store

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg store: Store)
}
