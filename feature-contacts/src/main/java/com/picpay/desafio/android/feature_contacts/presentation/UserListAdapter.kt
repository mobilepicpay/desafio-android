package com.picpay.desafio.android.feature_contacts.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.feature_contacts.databinding.ListItemUserBinding
import com.picpay.desafio.android.feature_contacts.models.UserPresentation
import com.picpay.desafio.android.shared_utilities.hideKeyboard
import com.picpay.desafio.android.shared_utilities.loadUrl
import java.util.*

class UserListAdapter(
    private val presentation: List<UserPresentation>,
    private val action: (UserPresentation) -> Unit
) : RecyclerView.Adapter<UserListItemViewHolder>() {

    private var filteredList: MutableList<UserPresentation> =
        emptyList<UserPresentation>().toMutableList()

    init {
        filteredList = presentation.toMutableList()
    }

    fun filter(text: String?) {
        filteredList = if (text.isNullOrEmpty()) {
            presentation.toMutableList()
        } else {
            filteredList.clear()

            for (user in presentation) {
                if (user.name.toLowerCase(Locale.getDefault())
                        .contains(text.toLowerCase(Locale.getDefault()))) {
                    filteredList.add(user)
                    break
                }
            }

            filteredList
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val itemBinding =
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(filteredList[position], action)
    }

    override fun getItemCount(): Int = filteredList.size

}

class UserListItemViewHolder(
    private val itemBinding: ListItemUserBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(user: UserPresentation, clickAction: (UserPresentation) -> Unit) {
        itemBinding.run {
            name.text = user.name
            username.text = user.username
            picture.loadUrl(imageUrl = user.img, isRoundView = true)
            root.setOnClickListener {
                it.hideKeyboard()
                clickAction.invoke(user) }
        }
    }
}