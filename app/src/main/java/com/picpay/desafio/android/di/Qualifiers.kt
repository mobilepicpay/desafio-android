package com.picpay.desafio.android.di

import javax.inject.Qualifier

/**
 * Annotation classes designed to allow Hilt to inject multiple bindings of the same super type,
 * in this example the CoroutineDispatcher. This way we can setup a scalable way to provide multiple
 * dispatchers in our di module.
 * */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher