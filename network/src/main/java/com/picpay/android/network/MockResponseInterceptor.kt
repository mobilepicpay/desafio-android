package com.picpay.android.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

abstract class MockResponseInterceptor : Interceptor {

    private val listEndPoints = mutableListOf<ConfigureResponseEndPoint>()

    override fun intercept(chain: Interceptor.Chain): Response {

        val urlPath = chain.request().url.encodedPath

        val selectedEndPoint = findEndPoint(urlPath)

        if (selectedEndPoint.urlPath.isEmpty()) {
            throw IllegalArgumentException("End point não encontrado! Verifique o parametro urlPath do seu objeto de configuração.")
        }

        return if (selectedEndPoint.isMock) {

            val message = selectedEndPoint.managerResponseMessage(chain.request())

            if (selectedEndPoint.delay > 0)
                Thread.sleep(selectedEndPoint.delay)

            Response.Builder()
                .code(selectedEndPoint.responseCode)
                .message(message)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(selectedEndPoint.getMessageByte(message))
                .addHeader("content-type", "application/json")
                .build()
        } else
            chain.proceed(chain.request())
    }

    fun addEndPoint(configureResponseEndPoint: ConfigureResponseEndPoint) {
        listEndPoints.add(configureResponseEndPoint)
    }

    private fun findEndPoint(endPoint: String): ConfigureResponseEndPoint {
        return listEndPoints.find { it.urlPath.contains(endPoint) } ?: ConfigureResponseEndPoint()
    }

    fun findEndPointByName(name: String): ConfigureResponseEndPoint {
        return listEndPoints.find { it.endPointName == name } ?: ConfigureResponseEndPoint()
    }

    open class ConfigureResponseEndPoint(
        var endPointName: String = "",
        var urlPath: List<String> = emptyList(),
        var customError: CustomError = CustomError(),
        var responseMessage: String = "",
        var responseCode: Int = 200,
        var delay: Long = 0,
        var isMock: Boolean = true
    ) {
        fun getMessageByte(message: String): ResponseBody {
            return message.toByteArray()
                .toResponseBody("application/json".toMediaTypeOrNull())
        }

        open fun managerResponseMessage(request: Request? = null): String {
            return if (responseCode < 299) {
                responseMessage
            } else {
                ""
            }
        }
    }
}