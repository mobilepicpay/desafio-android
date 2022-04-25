package com.picpay.desafio.android.commons.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract val viewModel: BaseViewModel
}