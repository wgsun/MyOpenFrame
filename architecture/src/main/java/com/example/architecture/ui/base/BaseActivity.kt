package com.example.architecture.ui.base

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecture.BaseApplication
import com.example.architecture.ui.page.DataBindingActivity

/**
 *@author:wgsun
 *2021/4/12
 *desc:
 */
abstract class BaseActivity : DataBindingActivity() {

    private var mActivityProvider: ViewModelProvider? = null

    private var mApplicationProvider: ViewModelProvider? = null

    fun <T : ViewModel> getActivityScopeViewModel(modelClass : Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(modelClass)
    }

    open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T? {
        if (mApplicationProvider == null) {
            mApplicationProvider = ViewModelProvider(application as BaseApplication, getAppFactory(this)!!)
        }
        return mApplicationProvider?.get(modelClass)
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory? {
        val application = checkApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
                ?: throw IllegalStateException("Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call.")
    }



}