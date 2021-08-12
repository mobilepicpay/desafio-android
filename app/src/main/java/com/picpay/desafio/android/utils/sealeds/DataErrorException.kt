package com.picpay.desafio.android.utils.sealeds

import androidx.annotation.StringRes
import com.picpay.desafio.android.R

sealed class DataErrorException(
    @StringRes val messageResId: Int,
    @StringRes val actionResId: Int? = null
) : Exception() {

    sealed class ApiErrorException(
        messageResId: Int,
        actionResId: Int?
    ) : DataErrorException(messageResId, actionResId) {
        object NetworkConnectionException : ApiErrorException(R.string.network_error, R.string.retry)
        object ServiceUnavailableException : ApiErrorException(R.string.service_unavailable_error, null)
        object NotFoundException : ApiErrorException(R.string.service_unavailable_error, null)
        object ForbiddenException : ApiErrorException(R.string.forbidden_error, null)
        object UnknownException : ApiErrorException(R.string.generic_error, R.string.retry)
    }

    sealed class DatabaseErrorException(
        messageResId: Int,
        actionResId: Int?
    ) : DataErrorException(messageResId, actionResId) {
        object SqliteException : DatabaseErrorException(R.string.database_error, R.string.retry)
        object UnknownException : DatabaseErrorException(R.string.generic_error, R.string.retry)
    }
}
