package com.picpay.desafio.android.shared

import android.content.Context
import android.content.Intent

object FeatureNavigator {
    fun openHome(context: Context) {
        Intent("feature.home.HomeActivity.OPEN").apply {
            setPackage(context.packageName)
            context.startActivity(this)
        }
    }
}