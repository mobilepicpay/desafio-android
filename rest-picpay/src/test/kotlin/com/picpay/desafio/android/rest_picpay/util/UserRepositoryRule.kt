package com.picpay.desafio.android.rest_picpay.util

import com.picpay.desafio.android.rest_picpay.api.PicPayUserService
import com.picpay.desafio.android.networking.NetworkBuilder
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource

internal class UserRepositoryRule : ExternalResource() {

    lateinit var server: MockWebServer
    lateinit var api: PicPayUserService

    override fun before() {
        super.before()
        server = MockWebServer()
        val url = server.url("/").toString()

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()

        api = NetworkBuilder(
            url = url.toHttpUrl().toString(),
            okHttpClient = client
        ).create(PicPayUserService::class.java)
    }

    override fun after() {
        server.shutdown()
        super.after()
    }

    fun defineScenario(status: Int, response: String = "") {

        server.enqueue(
            MockResponse().apply {
                setResponseCode(status)
                setBody(response)
            }
        )
    }
}
