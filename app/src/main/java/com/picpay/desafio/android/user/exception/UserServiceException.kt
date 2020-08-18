package com.picpay.desafio.android.user.exception

class UserServiceException(val originalException: Throwable) : Exception()
class UserDatabaseException(val originalException: Throwable) : Exception()