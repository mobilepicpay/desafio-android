package com.picpay.desafio.userlist.mainactivity

import com.picpay.desafio.userlist.utils.BaseInstrumentedTest
import org.junit.After
import org.junit.Test


class MainActivityTest : BaseInstrumentedTest() {

    @Test
    fun givenListFromService_shouldPresentList_withTitleOnScreen() {
        arrange {
            setDispatcher(getDispatcher())
            launchActivity()
        }
        action {
            onWait()
        }
        assert {
            hasContactTitle()
            listHasItems()
        }
    }

    @Test
    fun givenErrorFromService_shouldPresentErrorView() {
        arrange {
            useCaseWithCustomValue(flowException)
            launchActivity()
        }
        action {
            onWait()
        }
        assert {
            hasContactTitle()
            hasErrorView()
        }
    }

    @Test
    fun givenErrorFromService_andDatabaseEmpty_shouldPresentEmptyView() {
        arrange {
            useCaseWithCustomValue(flowEmpty)
            setDispatcher(getDispatcher(showError = true))
            launchActivity()
        }
        assert {
            hasContactTitle()
            hasEmptyView()
        }
    }

    @Test
    fun givenConfigurationChange_shouldKeepCurrentState() {
        arrange {
            setDispatcher(getDispatcher())
            launchActivity()
        }
        action {
            onWait()
            rotateScreen()
            setPortrait()
        }
        assert {
            hasContactTitle()
            listHasItems()
        }
    }
}