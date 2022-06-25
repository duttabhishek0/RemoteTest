package com.abhishek.remotetest.remote

import android.content.Context
import com.abhishek.remotetest.R
import com.abhishek.remotetest.util.HelperClass
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException

class StoreApiImpl(listener: Listener) : StoreApi(listener = listener) {
    var isInProgress = false

    companion object {
        private const val mainUrl = R.string.store_api.toString()
        private const val TAG = R.string.store_tag
    }

    private var request: Request = Request.Builder()
        .url(mainUrl)
        .build()

    fun fetchStore(context: Context) {

        if (HelperClass.isNetworkAvailable(context)) {
            listener.onNoNetwork()
            return
        }

        if (!isInProgress) {
            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(response: Response?) {

                    isInProgress = false
                    val responseText = response?.body()?.string()
                    if (responseText != null && responseText.isNotEmpty()) {
                        onSuccessApi(responseText)
                    } else {
                        onFailureApi("")
                    }
                }

                override fun onFailure(request: Request?, e: IOException?) {
                    isInProgress = false
                    onFailureApi("")
                }
            })
        }

        isInProgress = true
    }
}
