package com.picpay.desafio.android.uicomponents.listUser

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.picpay.desafio.android.model.UserDTO
import com.picpay.desafio.android.uicomponents.R

class UIListUser @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    private var recyclerView: RecyclerView
    private var loading: ShimmerFrameLayout

    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.list_user, this, true)

        recyclerView = findViewById(R.id.recyclerView)
        loading = findViewById(R.id.loading)
        recyclerView.adapter = adapter
    }

    fun starLoading() {
        loading.startShimmer()
        recyclerView.visibility = View.GONE
        loading.visibility = View.VISIBLE
    }

    fun stopLoading() {
        loading.stopShimmer()
        recyclerView.visibility = View.VISIBLE
        loading.visibility = View.GONE
    }

    fun setData(listUsers: List<UserDTO>) {
        adapter.users = listUsers
        stopLoading()
    }
}