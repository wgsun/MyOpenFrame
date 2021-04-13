package com.example.architecture.ui.page

import android.util.SparseArray
import androidx.lifecycle.ViewModel

/**
 *@author:wgsun
 *2021/4/12
 *desc: 为 base 页面中的 DataBinding 提供绑定项
 */
class DataBindingConfig {

    val layout: Int = 0

    val vmVariableIds: Int = 0

    val stateViewModel: ViewModel? = null

    val bindParams = SparseArray<Any?>()

    fun addBindingParam(variableId: Int, obj: Any) {
        if(bindParams[variableId] == null){
            bindParams.put(variableId, obj)
        }
    }
}