package com.picpay.desafio.android.feature.contacts.di.test

import com.picpay.desafio.android.core.data.di.test.testingDataModule
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.repository.fake.FakeUserLocalDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUsersRepository
import com.picpay.desafio.android.core.data.sync.test.testingSyncModule
import com.picpay.desafio.android.core.domain.di.testingDomainModule
import com.picpay.desafio.android.core.network.services.FakeUserService
import com.picpay.desafio.android.core.network.services.UserService
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val testingFeatureContactsModule = module {

    includes(testingSyncModule, testingDomainModule, testingDataModule)

    viewModelOf(::ContactsScreenViewModel)

    single<UserService> { FakeUserService() }

    single<UserLocalDataSource> { FakeUserLocalDataSource() }

    single<UserRemoteDataSource> { FakeUserRemoteDataSource() }

    singleOf(::FakeUsersRepository) { bind<UsersRepository>() }
}


//syncManager: SyncManager,
//searchLocalUsersFlowUseCase: SearchLocalUsersFlowUseCaseImpl,
//networkMonitor: NetworkMonitor