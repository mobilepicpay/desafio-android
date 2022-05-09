package com.picpay.desafio.android.presentation.userlist

import com.picpay.desafio.android.coreNetwork.models.Response
import com.picpay.desafio.android.di.IODispatcher
import com.picpay.desafio.android.di.PicpayDiModule
import com.picpay.desafio.android.domain.usecase.UsersInteractor
import com.picpay.desafio.android.presentation.model.UserPresentable
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Fake hilt module used to replace the actual DI
 * */
@TestInstallIn(components = [SingletonComponent::class], replaces = [PicpayDiModule::class])
@Module
internal object FakePicPayModule {

    @Provides
    fun provideFakeInteractor() = object : UsersInteractor {
        override suspend fun getUsers(): Flow<Response<List<UserPresentable>>> {
            return flow { emit(Response.Success(UserListTestData.USERS)) }
        }
    }

    @Provides
    @IODispatcher
    fun provideDispatcher() = Dispatchers.IO

}

