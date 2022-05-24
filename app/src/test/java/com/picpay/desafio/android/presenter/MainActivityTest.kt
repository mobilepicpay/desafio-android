package com.picpay.desafio.android.presenter

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.R
import com.picpay.desafio.android._config.BaseKoinTest
import com.picpay.desafio.android._config.RobolectricApplication
import com.picpay.desafio.android._config.readFile
import com.picpay.desafio.android.data.contact.model.ContactDTO
import com.picpay.desafio.android.data.contact.model.toContact
import com.picpay.desafio.android.domain.repository.ContactRepository
import com.picpay.desafio.android.presenter.viewmodel.ContactState
import com.picpay.desafio.android.presenter.viewmodel.ContactViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = RobolectricApplication::class, manifest = Config.NONE, sdk = [28])
class MainActivityTest : BaseKoinTest() {

    private val gson = Gson()

    @Test
    fun `activity should display contacts`() {
        // given
        val contactRepository: ContactRepository = let {
            val contactsDTO = gson.fromJson<List<ContactDTO>>(
                application.readFile("contact_list_200.json"),
                object : TypeToken<List<ContactDTO>>() {}.type
            )
            mock {
                onBlocking { getContacts() } doReturn Result.success(contactsDTO.map { it.toContact() })
            }
        }
        val viewModel = ContactViewModel(contactRepository)
        loadKoinModules(module {
            viewModel { viewModel }
        })
        val scenario = launchActivity<MainActivity>()
        // when
        scenario.moveToState(Lifecycle.State.RESUMED)
        // then
        Assert.assertTrue(viewModel.state.value is ContactState.Success)
        onView(withId(R.id.contact_list)).check { view, _ ->
            val recyclerView = view as RecyclerView
            Assert.assertTrue(recyclerView.isVisible)
            Assert.assertTrue(recyclerView.adapter!!.itemCount == 4) // header + 3 contacts
        }
        onView(withId(R.id.group_error)).check { view, _ ->
            Assert.assertTrue(!view.isVisible)
        }
        onView(withId(R.id.loading)).check { view, _ ->
            Assert.assertTrue(!view.isVisible)
        }
    }
}