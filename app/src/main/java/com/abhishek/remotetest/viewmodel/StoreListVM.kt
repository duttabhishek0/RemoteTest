package com.abhishek.remotetest.viewmodel

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhishek.remotetest.RemoteTestApp
import com.abhishek.remotetest.domain.entity.Store
import com.abhishek.remotetest.domain.entity.StoreResponse
import com.abhishek.remotetest.remote.Listener
import com.abhishek.remotetest.remote.StoreApi
import com.google.gson.Gson

class StoreListVM : ViewModel(), Listener {

    companion object {

        private val TAG = StoreListVM::class.java.simpleName
    }

    private lateinit var mActivityListener: ActivityListener

    val mStoresList: MutableLiveData<List<Store>> = MutableLiveData()

    private val mStoreApiCall: StoreApi by lazy { StoreApi(listener = this) }

    init {
        mStoresList.value = mutableListOf()
    }

    fun setView(viewListener: ActivityListener) {
        this.mActivityListener = viewListener
    }


    fun getStores(context: Context): MutableLiveData<List<Store>> {
        mStoreApiCall.callStores(context)
        RetrieveStores(mStoresList).execute()
        return mStoresList
    }

    override fun onSuccess(response: String) {

        val storeResponse = (Gson()).fromJson(response, StoreResponse::class.java)

        // Saving to database
        val appDatabase = RemoteTestApp.getAppDatabase()
        storeResponse.stores.forEach { store -> appDatabase.storeDoa().insertAll(store) }

        // making it available for UI
        mStoresList.postValue(appDatabase.storeDoa().getAll())
    }

    override fun onFailure(response: String) {
        mActivityListener.onApiCallError()
    }

    override fun onNoNetwork() {
        mActivityListener.onNoNetwork()
    }
}

class RetrieveStores(private val mStoreList: MutableLiveData<List<Store>>) : AsyncTask<Void, Void, List<Store>>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg empty: Void): List<Store> {

        val storeDoa = RemoteTestApp.getAppDatabase().storeDoa()
        return storeDoa.getAll()
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: List<Store>?) {
        super.onPostExecute(result)

        result?.let {
            mStoreList.postValue(result)
        }
    }

}