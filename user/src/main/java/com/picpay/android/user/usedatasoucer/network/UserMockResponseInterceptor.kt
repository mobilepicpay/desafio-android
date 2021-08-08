package com.picpay.android.user.usedatasoucer.network

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.android.network.Error
import com.picpay.android.network.MockResponseInterceptor
import com.picpay.android.user.R
import okhttp3.Request
import java.io.InputStream
import java.util.*

class UserMockResponseInterceptor(
    private val context: Context? = null
) : MockResponseInterceptor() {

    init {
    addEndPoint(object : MockResponseInterceptor.ConfigureResponseEndPoint(
        endPointName = "Recupera os usuarios",
        urlPath = listOf(
            "/picpay/api/users"
        ),
        responseMessage = readRawResource(R.raw.users_mock) ?: "",
        responseCode = 200,
        isMock = false,
        delay = 0,
        error = Error(errorMessage = "Erro ao acessar o endPoint")
    ) {
        override fun managerResponseMessage(request: Request?): String {

            return if (responseCode < 299) {

//                val dto = Gson().fromJson<GetFormResponseDTO>(responseJson, TypeToken.get(GetFormResponseDTO::class.java).type)
//                dto.currentScreen = screenType.screenName
//                Gson().toJson(dto)

                responseMessage

            } else {
                Gson().toJson(error)
            }
        }
    })
}

private fun readRawResource(@RawRes res: Int): String? {
    return readStream(context?.resources?.openRawResource(res))
}

private fun readStream(stream: InputStream?): String? {

    if(stream == null ) return ""

    val s: Scanner = Scanner(stream).useDelimiter("\\A")
    return if (s.hasNext()) s.next() else ""
}

private inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)
}
