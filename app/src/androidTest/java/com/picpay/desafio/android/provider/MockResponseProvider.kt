package com.picpay.desafio.android.provider

import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse

object MockResponseProvider {
    private const val SUCCESSFUL_RESPONSE = 200
    private const val ERROR_RESPONSE = 404

    fun usersMockResponse() = MockResponse().setResponseCode(SUCCESSFUL_RESPONSE)
        .setBody(Gson().toJson(MockContactProvider.mockedContacts()))

    fun errorMockResponse() = MockResponse().setResponseCode(ERROR_RESPONSE)
}