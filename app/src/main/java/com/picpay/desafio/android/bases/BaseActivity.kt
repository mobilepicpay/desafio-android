package com.picpay.desafio.android.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.picpay.desafio.android.extensions.viewModelClass
import org.koin.androidx.viewmodel.ext.android.getViewModel

abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {

    abstract val binding: ViewBinding
    val viewModel: V by lazy { getViewModel(clazz = viewModelClass()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}