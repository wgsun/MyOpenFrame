package com.example.architecture.ui.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 *@author:wgsun
 *2021/4/12
 *desc:
 */
abstract class DataBindingActivity : AppCompatActivity() {

    abstract fun getDataBindingConfig(): DataBindingConfig

    abstract fun initViewModel()

    private var mBinding: ViewDataBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        val dataBindingConfig = getDataBindingConfig()
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, dataBindingConfig.layout)
        binding.lifecycleOwner = this
        binding.setVariable(dataBindingConfig.vmVariableIds, dataBindingConfig.stateViewModel)
        val bindParams = dataBindingConfig.bindParams
        bindParams.forEach { key, value -> binding.setVariable(key, value) }
        mBinding = binding
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }

}