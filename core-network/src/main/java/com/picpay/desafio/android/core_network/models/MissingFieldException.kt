package com.picpay.desafio.android.core_network.models

class MissingFieldException(fieldName: String) : Throwable("Field $fieldName is missing")