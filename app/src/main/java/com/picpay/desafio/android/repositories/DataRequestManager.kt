package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.utils.sealeds.DataConsumptionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class DataRequestManager @Inject constructor() {

    suspend inline fun <T, reified R> performDataRequestManagement(
        noinline loadLocalData: (suspend () -> T)? = null,
        crossinline shouldFetchData: () -> Boolean,
        crossinline onFetchSucceeded: suspend () -> T,
        crossinline onFetchFailed: () -> Unit = {}
    ): Flow<DataConsumptionStatus<T, R>> {
        return flow {
            try {
                loadLocalData?.let {
                    emit(DataConsumptionStatus.Success(loadLocalData()))
                }

                if (shouldFetchData()) {
                    emit(DataConsumptionStatus.Loading(true))
                    emit(DataConsumptionStatus.Success(onFetchSucceeded()))
                    emit(DataConsumptionStatus.Loading(false))
                }
            } catch (exception: Exception) {
                onFetchFailed()
                if (exception is R) {
                    emit(DataConsumptionStatus.Error(exception))
                }
                emit(DataConsumptionStatus.Loading(false))
            }
        }.flowOn(Dispatchers.IO)
    }
}
