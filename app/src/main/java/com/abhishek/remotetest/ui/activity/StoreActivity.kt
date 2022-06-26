package com.abhishek.remotetest.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abhishek.remotetest.R
import com.abhishek.remotetest.databinding.ActivityStoreBinding
import com.abhishek.remotetest.ui.fragment.MapFragment
import com.abhishek.remotetest.viewmodel.StoreVM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_store_item.*

class StoreActivity : AppCompatActivity() {

    companion object {

        private val TAG = StoreActivity::class.java.simpleName

        private const val STORE_ID_KEY = "STORE_ID_KEY"

        fun startWithStoreId(context: Context, storeId: String) {

            val intent = Intent(context, StoreActivity::class.java)
            intent.putExtra(STORE_ID_KEY, storeId)
            context.startActivity(intent)
        }
    }

    private lateinit var storeVM: StoreVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storeBinding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(storeBinding.root)
        setSupportActionBar(storeBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initializeUi(storeBinding)
    }

    @SuppressLint("SetTextI18n")
    private fun initializeUi(binding: ActivityStoreBinding) {
        storeVM = ViewModelProviders.of(this).get(StoreVM::class.java)
        val storeId = intent.getStringExtra(STORE_ID_KEY)
        if (storeId != null) {
            storeVM.getStore(storeId).observe(
                this,
                Observer { store ->

                    binding.toolbar.title = store?.name

                    if (URLUtil.isValidUrl(store?.storeLogoURL)) {
                        Picasso.get().load(store?.storeLogoURL).into(binding.storeLogIv)
                    }

                    address_tv.text = store?.address
                    city_tv.text = store?.city
                    zip_code_tv.text = store?.zipcode
                    state_tv.text = store?.state
                    phone_tv.text = "Contact: ${store?.phone}"

                    val fragment =
                        MapFragment.newInstance(store?.name!!, store.latitude!!, store.longitude!!)
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.map_container, fragment).commit()
                }
            )
        }
    }
}
