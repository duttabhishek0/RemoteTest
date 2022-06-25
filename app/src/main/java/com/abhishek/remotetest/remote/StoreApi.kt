package com.abhishek.remotetest.remote

import com.abhishek.remotetest.R

abstract class StoreApi(protected val listener: Listener) {
    companion object {
        private const val TAG = R.string.store_tag
    }

    fun onSuccessApi(response: String) {
        listener.onSuccess(response)
    }
    fun onFailureApi(response: String) {
        listener.onFailure(response)
    }
    fun onNoNetworkApi() {
        listener.onNoNetwork()
    }
}

interface Listener {
    fun onSuccess(response: String)

    fun onFailure(response: String)

    fun onNoNetwork()
}
