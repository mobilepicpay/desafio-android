package com.picpay.desafio.android.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NetworkClientOkHttpTest {

    @Test
    fun retrofitBuilder_returns_Retrofit_instance_with_correct_baseUrl() {
        // Given
        val baseUrl = "https://jsonplaceholder.typicode.com/"
        val okHttpClient = OkHttpClient.Builder().build()

        // When
        val retrofit = NetworkClient.retrofitBuilder(baseUrl, okHttpClient)

        // Then
        assertNotNull(retrofit, "Retrofit instance should not be null")
        assertEquals(baseUrl, retrofit.baseUrl().toString(), "Base URL should match the provided value")
    }

    @Test
    fun retrofitBuilder_uses_GsonConverterFactory() {
        // Given
        val baseUrl = "https://jsonplaceholder.typicode.com/"
        val okHttpClient = OkHttpClient.Builder().build()

        // When
        val retrofit = NetworkClient.retrofitBuilder(baseUrl, okHttpClient)

        // Then
        assertNotNull(retrofit, "Retrofit instance should not be null")
        val converterFactory = retrofit.converterFactories().find { it is GsonConverterFactory }
        assertNotNull(converterFactory, "GsonConverterFactory should be included in Retrofit instance")
    }

    @Test
    fun okHttpClientBuilder_returns_OkHttpClient_with_HttpLoggingInterceptor() {
        // When
        val okHttpClient = NetworkClient.okHttpClientBuilder()

        // Then
        assertNotNull(okHttpClient, "OkHttpClient instance should not be null")
        val interceptor = okHttpClient.interceptors.find { it is HttpLoggingInterceptor }
        assertNotNull(interceptor, "HttpLoggingInterceptor should be included in OkHttpClient instance")
    }

    @Test
    fun okHttpClientBuilder_sets_HttpLoggingInterceptor_to_BODY_level() {
        // When
        val okHttpClient = NetworkClient.okHttpClientBuilder()

        // Then
        val interceptor = okHttpClient.interceptors.find { it is HttpLoggingInterceptor } as? HttpLoggingInterceptor
        assertNotNull(interceptor, "HttpLoggingInterceptor should be included in OkHttpClient instance")
        assertEquals(interceptor.level, HttpLoggingInterceptor.Level.BODY, "HttpLoggingInterceptor should be set to BODY level")
    }
}