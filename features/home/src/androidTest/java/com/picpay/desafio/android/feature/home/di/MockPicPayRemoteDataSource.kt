package com.picpay.desafio.android.feature.home.di

import com.picpay.desafio.android.shared.data.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.shared.data.remote.UserRemote

class MockPicPayRemoteDataSource: PicPayRemoteDataSource {
    override suspend fun getUserList(): List<UserRemote> {
        return emptyList()
    }
}