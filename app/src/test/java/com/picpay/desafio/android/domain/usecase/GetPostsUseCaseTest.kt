package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.Post
import com.picpay.desafio.android.domain.repository.PostRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetPostsUseCaseTest {

    private val postRepository = mock(PostRepository::class.java)

    private val getPostsUseCase = GetPostsUseCase(postRepository)

    @Test
    fun `invoke should return posts from repository`() = runBlocking {
        // given
        val expectedPosts = listOf(
            Post(1, 1, "Test Title 1", "Test Body 1"),
            Post(2, 2, "Test Title 2", "Test Body 2")
        )
        `when`(postRepository.getPosts()).thenReturn(expectedPosts)

        // when
        val result = getPostsUseCase()

        // then
        assertEquals(expectedPosts, result)
    }

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runBlocking {
        // given
        val expectedPosts = emptyList<Post>()
        `when`(postRepository.getPosts()).thenReturn(expectedPosts)

        // when
        val result = getPostsUseCase()

        // then
        assertEquals(expectedPosts, result)
    }
}
