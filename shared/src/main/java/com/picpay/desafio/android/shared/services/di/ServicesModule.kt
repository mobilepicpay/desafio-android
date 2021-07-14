package com.picpay.desafio.android.shared.services.di

import com.picpay.desafio.android.shared.services.PicPayService
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author Vitor Herrmann on 12/07/2021
 */
object ServicesModule {

    val module = module {
        factory { get<Retrofit>().create(PicPayService::class.java) }
    }
}
