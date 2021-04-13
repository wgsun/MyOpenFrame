package com.example.architecture

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 *@author:wgsun
 *2021/4/12
 *desc:
 */
class BaseApplication: Application(), ViewModelStoreOwner{

    private var mAppViewModelStore: ViewModelStore? = null

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore!!
    }

}