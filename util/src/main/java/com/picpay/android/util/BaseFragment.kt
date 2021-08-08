package com.picpay.android.util

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

abstract class BaseFragment(layout: Int = 0) : Fragment(layout) {

    private var hostActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            hostActivity = context
        }
    }

    fun statusLoad(status: Boolean) {
        hostActivity?.loadState(status)
    }

    fun showWarningMessage(message: String) {
        hostActivity?.showWarningMessage(message)
    }

    fun showWarningMessage(message: Int) {
        hostActivity?.showWarningMessage(messageRes = message)
    }

    fun navigateTo(fragmentId: Int) {
        view?.run {
            Navigation.findNavController(this).navigate(fragmentId)
        }
    }

    fun navigateTo(navDirections: NavDirections) {
        view?.run {
            Navigation.findNavController(this).navigate(navDirections)
        }
    }

}