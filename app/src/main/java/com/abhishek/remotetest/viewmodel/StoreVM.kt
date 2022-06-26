package com.abhishek.remotetest.viewmodel

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.remotetest.RemoteTestApp
import com.abhishek.remotetest.domain.entity.Store

class StoreVM : ViewModel() {

    private val mStore: MutableLiveData<Store> = MutableLiveData()

    fun getStore(storeId: String): MutableLiveData<Store> {

        RetrieveStore(mStore).execute(storeId)

        return mStore
    }
}

class RetrieveStore(private val mStore: MutableLiveData<Store>) : AsyncTask<String, Void, Store>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg storeId: String): Store {

        val storeDoa = RemoteTestApp.getAppDatabase().storeDao()
        return storeDoa.storeByIds(storeID = storeId[0])
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Store?) {
        super.onPostExecute(result)

        result?.let {
            mStore.postValue(result)
        }
    }
}
