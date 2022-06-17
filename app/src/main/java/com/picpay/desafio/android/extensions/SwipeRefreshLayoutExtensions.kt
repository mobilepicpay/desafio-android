package com.picpay.desafio.android.extensions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.picpay.desafio.android.R

fun SwipeRefreshLayout.updateRefreshing(isRefreshing: Boolean) {
    if (this.isRefreshing != isRefreshing)
        this.isRefreshing = isRefreshing
}

fun SwipeRefreshLayout.stopRefreshing() {
    this.isRefreshing = false
}

fun SwipeRefreshLayout.setTheme() {
    setColorSchemeResources(R.color.colorAccent)
    setProgressBackgroundColorSchemeResource(R.color.colorPrimaryDark)
}