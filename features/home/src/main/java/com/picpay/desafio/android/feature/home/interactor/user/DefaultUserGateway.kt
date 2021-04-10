package com.picpay.desafio.android.feature.home.interactor.user

class DefaultUserGateway : UserGateway {
    override fun getUserList(): List<UserEntity> {
        val url = "http://4.bp.blogspot.com/-dEE3Fl9LA68/TaZv6og1o0I/AAAAAAAAArY/uZF_Bwx-aoI/s1600/kina.png"
        return arrayListOf(
            UserEntity(1, "Andre", "Spinel", url),
            UserEntity(1, "Andre", "Spinel", url),
            UserEntity(1, "Andre", "Spinel", url),
            UserEntity(1, "Andre", "Spinel", url),
        )
    }
}