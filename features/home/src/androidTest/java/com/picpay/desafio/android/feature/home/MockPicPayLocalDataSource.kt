package com.picpay.desafio.android.feature.home

import com.picpay.desafio.android.shared.data.local.PicPayLocalDataSource
import com.picpay.desafio.android.shared.data.local.UserLocal

class MockPicPayLocalDataSource: PicPayLocalDataSource {
    override suspend fun getUserList(): List<UserLocal> {
        return emptyList()
    }

    override suspend fun insertAll(list: List<UserLocal>) {
        // Nothing
    }
}