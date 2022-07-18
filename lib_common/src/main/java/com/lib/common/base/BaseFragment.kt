package com.lib.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
abstract class BaseFragment<T : ViewDataBinding, B: BaseViewModel> : Fragment() {

    lateinit var mBinding: T

    lateinit var mViewModel: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<B>
        mViewModel = ViewModelProvider(this).get(viewModelClass)
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mBinding.root
    }

    protected abstract fun getLayoutId(): Int

}