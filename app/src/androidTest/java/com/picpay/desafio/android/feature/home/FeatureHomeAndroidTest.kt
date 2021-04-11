package com.picpay.desafio.android.feature.home

import com.picpay.desafio.android.BaseAndroidTest
import org.junit.Test

class FeatureHomeAndroidTest : BaseAndroidTest() {

    @Test
    fun homeActivity_SHOULD_LoadFromDataFromRemote() {
        HomeBot()
            .checkItemIsDisplayedByText("Eduardo Santos")
            .checkItemIsDisplayedByText("Felipe Andrade")
    }
}