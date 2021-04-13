package com.example.architecture.ui.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 *@author:wgsun
 *2021/4/13
 *desc:
 */
abstract class DatabindingFragment : Fragment() {

    var mActivity: AppCompatActivity? = null

    var mBinding: ViewDataBinding? = null

    abstract fun initViewModel()

    abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBindingConfig = getDataBindingConfig()
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, dataBindingConfig.layout, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(dataBindingConfig.vmVariableIds, dataBindingConfig.stateViewModel)
        val bindParams = dataBindingConfig.bindParams
        bindParams.forEach { key, value -> binding.setVariable(key, value)}
        mBinding = binding
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding?.unbind()
    }
}