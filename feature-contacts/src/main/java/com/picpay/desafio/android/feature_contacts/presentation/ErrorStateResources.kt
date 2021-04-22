package com.picpay.desafio.android.feature_contacts.presentation

import com.picpay.desafio.android.domain.errors.NetworkingError
import com.picpay.desafio.android.domain.errors.RemoteServiceError
import com.picpay.desafio.android.feature_contacts.R

data class ErrorStateResources(
    val image: Int,
    val message: Int
) {
    companion object {
        operator fun invoke(error: Throwable) =
            when (error) {
                is RemoteServiceError.RemoteSystem -> ErrorStateResources(
                    R.drawable.img_server_down,
                    R.string.error_server_down
                )
                is NetworkingError -> ErrorStateResources(
                    R.drawable.img_network_issue,
                    R.string.error_network
                )
                else -> ErrorStateResources(
                    R.drawable.img_bug_found,
                    R.string.error_bug_found
                )
            }
    }
}
