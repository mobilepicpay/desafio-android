package com.picpay.desafio.android.data._config.network

import android.annotation.SuppressLint
import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object ClientFactory {

    const val CACHE_SIZE = (10 * 1024 * 1024).toLong() // 10MB

    private const val CONNECTION_TIMEOUT = 1L
    private const val READ_TIMEOUT = 1L
    private const val WRITE_TIMEOUT = 15L

    private val gson = GsonBuilder().create()

    private fun unsafeClient(): OkHttpClient.Builder.() -> Unit = {
        try {
            val trustAllCerts: Array<TrustManager> = arrayOf(
                @SuppressLint("CustomX509TrustManager")
                object : X509TrustManager {

                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) = Unit

                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) = Unit

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            val sslContext = SSLContext.getInstance("SSL").apply {
                init(null, trustAllCerts, SecureRandom())
            }
            sslSocketFactory(
                sslContext.socketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            hostnameVerifier { _, _ -> true }
        } catch (e: Exception) {
        }
    }

    fun buildOkHttpClient(
        cache: Cache? = null,
        builder: OkHttpClient.Builder.() -> Unit = { }
    ): OkHttpClient = OkHttpClient.Builder().apply {
        // config cache
        cache(cache)
        // apply default params
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES)
        readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        // apply unsafe okHttpClient if current android api is below 26
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            apply(unsafeClient())
        }
        // apply builder params
        apply(builder)
    }.build()

    fun buildRetrofit(
        host: String = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/",
        client: OkHttpClient,
    ): Retrofit = Retrofit.Builder().apply {
        baseUrl(host)
        addConverterFactory(GsonConverterFactory.create(gson))
        client(client)
    }.build()
}