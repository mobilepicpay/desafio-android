package com.picpay.desafio.android.providers

import com.picpay.desafio.android.extensions.toJson
import okhttp3.mockwebserver.MockResponse

object MockResponseProvider {

    private const val SUCCESS_RESPONSE_CODE = 200
    private const val ERROR_RESPONSE_CODE = 404

    fun usersMockResponse() = MockResponse().setResponseCode(SUCCESS_RESPONSE_CODE)
        .setBody(MockAndroidUserProvider.mockedUsers().toJson())

    fun errorMockResponse() = MockResponse().setResponseCode(ERROR_RESPONSE_CODE)
}