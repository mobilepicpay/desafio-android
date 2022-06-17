package com.picpay.desafio.android

import com.picpay.desafio.android.bases.BaseActivity
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.extensions.viewBinding

class MainActivity : BaseActivity<Nothing>() {
    override val binding by viewBinding(ActivityMainBinding::inflate)
}
