package com.abhishek.remotetest.domain.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "store")
class Store {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "storeID")
    @SerializedName("storeID")
    var storeID: String? = null

    @ColumnInfo(name = "address")
    @SerializedName("address")
    var address: String? = null

    @ColumnInfo(name = "city")
    @SerializedName("city")
    var city: String? = null

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null

    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    var latitude: String? = null

    @ColumnInfo(name = "zipcode")
    @SerializedName("zipcode")
    var zipcode: String? = null

    @ColumnInfo(name = "storeLogoURL")
    @SerializedName("storeLogoURL")
    var storeLogoURL: String? = null

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    var phone: String? = null

    @ColumnInfo(name = "longitude")
    @SerializedName("longitude")
    var longitude: String? = null

    @ColumnInfo(name = "state")
    @SerializedName("state")
    var state: String? = null
}

class StoreResponse {

    @SerializedName("stores")
    var stores: List<Store> = mutableListOf()
}
