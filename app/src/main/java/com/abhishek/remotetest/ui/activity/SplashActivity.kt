package com.abhishek.remotetest.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.abhishek.remotetest.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : RootActivity() {
    companion object {
        private const val SPLASH_DELAY = 1500L
    }

    private val mHandler = Handler(Looper.getMainLooper()).postDelayed({
        onStart()
    }, SPLASH_DELAY)

    private val mSplashRunnable = Runnable {
        startActivity(Intent(baseContext, StoreListActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
