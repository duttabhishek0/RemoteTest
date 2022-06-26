package com.abhishek.remotetest.ui.activity

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * Root activity including the common
 * attributes to be included in other activities.
 *
 */
abstract class RootActivity : AppCompatActivity(), ActivityListener {

    lateinit var rootView: ViewGroup

    override fun onNoNetwork() {
        Snackbar.make(rootView, "No network connection :(", Snackbar.LENGTH_LONG).show()
    }

    override fun onApiCallError() {
        Snackbar.make(rootView, "Something went wrong :(", Snackbar.LENGTH_LONG).show()
    }
}

interface ActivityListener {

    fun onNoNetwork()

    fun onApiCallError()
}
