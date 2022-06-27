package com.abhishek.remotetest.ui.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.abhishek.remotetest.R
import com.abhishek.remotetest.domain.entity.Store
import com.abhishek.remotetest.ui.adapter.StoreAdapter
import com.abhishek.remotetest.ui.adapter.StoreListener
import com.abhishek.remotetest.viewmodel.StoreListVM
import kotlinx.android.synthetic.main.activity_store.toolbar
import kotlinx.android.synthetic.main.activity_store_list.*
import kotlinx.android.synthetic.main.content_store_list.*

class StoreListActivity : RootActivity(), StoreListener {

    companion object {
        private val TAG = StoreListActivity::class.java.simpleName
    }

    private lateinit var storeListVM: StoreListVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)
        setSupportActionBar(toolbar)
        rootView = root_view_CL

        storeListVM = ViewModelProvider(this).get(StoreListVM::class.java)
        storeListVM.setView(this)
        storeListVM.getStores(baseContext)

        storeListVM.mStoresList.observe(
            this
        ) { storeList ->

            storeList?.let {
                if (storeList.isNotEmpty()) {

                    loading_pb.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    val adapter = StoreAdapter(storeList, this)
                    recycler_view.adapter = adapter
                }
            }
        }
    }

    override fun onStoreClick(store: Store) {
        StoreActivity.startWithStoreId(this, store.storeID!!)
    }
}
