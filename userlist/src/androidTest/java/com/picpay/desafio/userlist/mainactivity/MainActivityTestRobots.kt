package com.picpay.desafio.userlist.mainactivity

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.google.gson.Gson
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.common.utils.JsonReader
import com.picpay.desafio.userlist.R
import com.picpay.desafio.userlist.di.UserListModule
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCase
import com.picpay.desafio.userlist.domain.usecase.GetUserListUseCaseImpl
import com.picpay.desafio.userlist.presentation.MainActivity
import com.picpay.desafio.userlist.presentation.viewmodel.UserListViewModel
import com.picpay.desafio.userlist.utils.OrientationChangeAction
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mockito
import java.security.InvalidParameterException
import kotlin.coroutines.coroutineContext

fun MainActivityTest.arrange(func: MainActivityTestArrange.() -> Unit) =
    MainActivityTestArrange().apply { func() }

fun MainActivityTest.action(func: MainActivityTestAct.() -> Unit) =
    MainActivityTestAct().apply { func() }

fun MainActivityTest.assert(func: MainActivityTestAssert.() -> Unit) =
    MainActivityTestAssert().apply { func() }

private lateinit var activity: MainActivity

 val flowException = flow<Resource<List<User>>> {
    emit(Resource.error(InvalidParameterException()))
}

val flowEmpty = flow<Resource<List<User>>> {
    emit(Resource.empty())
}

class MainActivityTestArrange {
    fun launchActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            activity = it
        }
    }

    fun getDispatcher(showError: Boolean = false): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    showError -> MockResponse().setResponseCode(500)
                    request.path!!.contains("/users") -> {
                        MockResponse().setResponseCode(200)
                            .setBody(JsonReader.readMockedJson("userlist.json"))
                    }
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }
    }

    @DelicateCoroutinesApi
    fun useCaseWithCustomValue(value:Flow<Resource<List<User>>>) {
        val useCase = Mockito.mock(GetUserListUseCase::class.java)
        GlobalScope.launch {
            Mockito.`when`(useCase.execute(Unit)).thenReturn(value)
        }
        loadKoinModules(
            module {
                listOf(UserListModule.modules)
                single(override = true) { useCase }
            }
        )
    }
}

class MainActivityTestAct {

    fun rotateScreen() {
        Espresso.onView(isRoot()).perform(OrientationChangeAction.orientationLandscape())
    }

    fun setPortrait() {
        Espresso.onView(isRoot()).perform(OrientationChangeAction.orientationPortrait())
    }

    fun onWait(delay: Long = 2000L){
        Espresso.onView(isRoot()).perform(waitFor(delay))
    }

    private fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}

class MainActivityTestAssert {

    fun hasContactTitle() {
        Espresso.onView(ViewMatchers.withText(R.string.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun listHasItems() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)))
    }

    fun hasErrorView() {
        Espresso.onView(ViewMatchers.withId(R.id.cv_error_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun hasEmptyView() {
        Espresso.onView(ViewMatchers.withId(R.id.cv_empty_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}