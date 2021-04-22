package com.picpay.desafio.android.feature_contacts.presentation

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.feature_contacts.databinding.ActivityUsersBinding
import com.picpay.desafio.android.feature_contacts.models.UserPresentation
import com.picpay.desafio.android.shared_utilities.gone
import com.picpay.desafio.android.shared_utilities.viewBinding
import com.picpay.desafio.android.shared_utilities.visible
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserActivity : AppCompatActivity() {

    private val viewBindings by viewBinding(ActivityUsersBinding::inflate)
    private val viewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBindings.root)
        setup()
    }

    private fun refresh() {
        viewModel.handle(UserInteraction.RequestedFreshContent)
    }

    private fun setup() {
        viewBindings.run {
            setSupportActionBar(toolbar)
            usersRecyclerView.layoutManager = LinearLayoutManager(this@UserActivity)
            usersSwipeToRefresh.setOnRefreshListener { refresh() }
        }

        lifecycleScope.launch {
            viewModel.bind().collect { renderState(it) }
        }
    }

    private fun renderState(state: UsersScreenState) {
        when (state) {
            is UsersScreenState.Idle -> launch()
            is UsersScreenState.Loading -> startExecution()
            is UsersScreenState.Success -> {
                Log.i("State", "Success -> fetched")
                showUsers(state.value)
            }
            is UsersScreenState.Failed -> {
                Log.i("State", "Error -> $state")
                handleError(state.reason)
            }
        }
    }

    private fun showUsers(presentation: List<UserPresentation>) {
        viewBindings.run {
            val input = viewBindings.queryTextInput

            val adapter = UserListAdapter(presentation) { userPresentation ->
                input.clearFocus()
                toast(userPresentation.name)
            }


            usersSwipeToRefresh.isRefreshing = false
            usersRecyclerView.adapter = adapter

            input.run {
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        input.editableText.toString()
                    }
                    return@setOnEditorActionListener false
                }

                addTextChangedListener { input: Editable? ->
                    input?.let {
                        adapter.filter(it.toString())
                    }
                }
            }
        }
    }

    private fun handleError(failed: Throwable) {
        Log.e("Error", "Error -> $failed")

        viewBindings.run {
            usersSwipeToRefresh.isRefreshing = false

            val (errorImage, errorMessage) = ErrorStateResources(failed)
            val hasPreviousContent =
                usersRecyclerView.adapter
                    ?.let { it.itemCount != 0 }
                    ?: false

            when {
                hasPreviousContent -> toast(errorMessage)
                else -> showErrorState(errorImage, errorMessage)
            }
        }
    }

    private fun showErrorState(errorImage: Int, errorMessage: Int) {
        with(viewBindings) {
            errorStateView.visible()
            errorStateImage.setImageResource(errorImage)
            errorStateLabel.setText(errorMessage)
            retryButton.setOnClickListener { launch() }
        }
    }

    private fun launch() {
        viewModel.handle(UserInteraction.OpenedScreen)
    }

    private fun startExecution() {
        viewBindings.run {
            errorStateView.gone()
            usersSwipeToRefresh.isRefreshing = true
        }
    }
}

fun AppCompatActivity.toast(message: Int) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.toast(toaster: String) =
    Toast.makeText(this, toaster, Toast.LENGTH_SHORT).show()