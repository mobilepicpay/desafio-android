package com.picpay.desafio.android.commons.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SchedulerProvider {
    val ui: Scheduler
    val io: Scheduler
    val computation: Scheduler
}

class SchedulerProviderImpl : SchedulerProvider {
    override val ui: Scheduler get() = AndroidSchedulers.mainThread()
    override val io: Scheduler get() = Schedulers.io()
    override val computation: Scheduler get() = Schedulers.computation()
}