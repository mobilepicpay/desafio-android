package com.picpay.desafio.android.modules.base

import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.utils.pokos.ErrorMessageViewObject

open class BaseActivity : AppCompatActivity() {

    private val loadingAnimation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.loading_animation)
    }

    protected fun showLoading(isLoading: Boolean, view: View) {
        view.isVisible = isLoading

        if (isLoading) {
            view.startAnimation(loadingAnimation)
        } else {
            view.clearAnimation()
        }
    }

    protected fun showShortErrorMessage(
        errorMessageViewObject: ErrorMessageViewObject,
        contextView: View,
        action: (() -> Unit)? = null
    ) {
        Snackbar.make(contextView, errorMessageViewObject.messageResId, Snackbar.LENGTH_SHORT).run {
            errorMessageViewObject.actionResId?.let {
                setAction(it) { action?.invoke() }
            }
            show()
        }
    }
}
