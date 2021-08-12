package com.picpay.desafio.android.utils.sealeds

sealed class DataConsumptionStatus<out DataType, out ExceptionType> {
    data class Success<out DataType>(val data: DataType) : DataConsumptionStatus<DataType, Nothing>()
    data class Error<ExceptionType>(val exception: ExceptionType) : DataConsumptionStatus<Nothing, ExceptionType>()
    data class Loading(val isLoading: Boolean) : DataConsumptionStatus<Nothing, Nothing>()
}
