package com.example.architecture.ui.base

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecture.BaseApplication
import com.example.architecture.ui.page.DatabindingFragment

/**
 *@author:wgsun
 *2021/4/13
 *desc:
 */
abstract class BaseFragment : DatabindingFragment() {

    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null


    fun <T : ViewModel> getFragmentScopeViewModel(modelClass: Class<T>): T? {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider?.get(modelClass)
    }

    fun <T : ViewModel> getActivityScopeViewModel(modelClass: Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(mActivity!!)
        }
        return mActivityProvider?.get(modelClass)
    }

    fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T? {
        if (mApplicationProvider == null) {
            mApplicationProvider = ViewModelProvider(mActivity!!.applicationContext as BaseApplication, getAppFactory(mActivity!!)!!)
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