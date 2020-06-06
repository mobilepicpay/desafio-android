package com.picpay.desafio.android.utils

import com.picpay.desafio.android.room.models.StringKeyValuePair

object Utils {

    fun shouldCallApi(
        lastApiCallMillis: String,
        cacheThresholdInMillis: Long = 300000L //default value is 5 minutes//
    ): Boolean {
        return (System.currentTimeMillis() - lastApiCallMillis.toLong()) >= cacheThresholdInMillis
    }

    fun getCurrentTimeKeyValuePair(key: String): StringKeyValuePair {
        return StringKeyValuePair(key, System.currentTimeMillis().toString())
    }
}