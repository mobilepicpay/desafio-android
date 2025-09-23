package com.picpay.desafio.android.presentation.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.Post
import com.picpay.desafio.android.domain.usecase.GetPostsUseCase
import kotlinx.coroutines.launch

sealed class PostsState {
    object Loading : PostsState()
    data class Success(val posts: List<Post>) : PostsState()
    data class Error(val message: String) : PostsState()
}

class PostsViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {
    private val _state = MutableLiveData<PostsState>(PostsState.Loading)
    val state: LiveData<PostsState> get() = _state

    fun loadPosts() {
        _state.value = PostsState.Loading
        viewModelScope.launch {
            try {
                val posts = getPostsUseCase()
                _state.value = PostsState.Success(posts)
            } catch (e: Exception) {
                _state.value = PostsState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}
