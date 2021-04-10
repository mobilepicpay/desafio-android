package com.picpay.desafio.android.feature.home

import android.content.Context
import com.picpay.desafio.android.shared.FeatureNavigator

object HomeNavigator : FeatureNavigator() {
    override fun navigate(context: Context) {
        startInternalActivity(context, "com.picpay.desafio.android.feature.home.OPEN")
    }
}