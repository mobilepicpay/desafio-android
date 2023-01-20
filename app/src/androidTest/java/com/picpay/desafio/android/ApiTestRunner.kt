package com.picpay.desafio.android

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnitRunner
import com.picpay.desafio.android.data.source.local.AppDatabase
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.remote.PicPayService
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTestRunner : AndroidJUnitRunner() {

    override fun callApplicationOnCreate(app: Application?) {
        super.callApplicationOnCreate(app)
        loadKoinModules(
            module {
                single { createTestWebService<PicPayService>(get()) }
                single { createTestDatabase() }
                single { createTestDao(get()) }
            }
        )
    }

    private fun createTestDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    private inline fun <reified T> createTestWebService(okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:$MOCK_WEB_SERVER_PORT/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(T::class.java)
    }

    private fun createTestDatabase(): AppDatabase {
        val context = ApplicationProvider.getApplicationContext<Context>()

        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
    }

    companion object {
        const val MOCK_WEB_SERVER_PORT = 8080
    }
}
