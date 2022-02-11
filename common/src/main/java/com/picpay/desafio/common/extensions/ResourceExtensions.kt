package com.picpay.desafio.common.extensions

import com.picpay.desafio.common.base.Resource


fun <T> Resource<T>.isSuccess():Boolean = status == Resource.Status.SUCCESS
fun <T> Resource<T>.isError():Boolean = status == Resource.Status.ERROR
fun <T> Resource<T>.isEmpty():Boolean = status == Resource.Status.EMPTY