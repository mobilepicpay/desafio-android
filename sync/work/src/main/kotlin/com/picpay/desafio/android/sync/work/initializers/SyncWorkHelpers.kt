package com.picpay.desafio.android.sync.work.initializers

import androidx.work.Constraints
import androidx.work.NetworkType

val SyncConstraints
    get() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
