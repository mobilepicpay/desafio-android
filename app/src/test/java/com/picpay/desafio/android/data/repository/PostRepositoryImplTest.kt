package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.PostResponse
import com.picpay.desafio.android.data.remote.PostsService
import com.picpay.desafio.android.domain.model.Post
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PostRepositoryImplTest {

    private val postsService = mock(PostsService::class.java)

    private val postRepository = PostRepositoryImpl(postsService)

    @Test
    fun `getPosts should return mapped posts when service succeeds`() = runBlocking {
        // given
        val postResponses = listOf(
            PostResponse(1, 1, "Test Title 1", "Test Body 1"),
            PostResponse(2, 2, "Test Title 2", "Test Body 2")
        )
        val expectedPosts = listOf(
            Post(1, 1, "Test Title 1", "Test Body 1"),
            Post(2, 2, "Test Title 2", "Test Body 2")
        )
        `when`(postsService.getPosts()).thenReturn(postResponses)

        // when
        val result = postRepository.getPosts()

        // then
        assertEquals(expectedPosts, result)
    }

    @Test
    fun `getPosts should return empty list when service returns empty list`() = runBlocking {
        // given
        val postResponses = emptyList<PostResponse>()
        val expectedPosts = emptyList<Post>()
        `when`(postsService.getPosts()).thenReturn(postResponses)

        // when
        val result = postRepository.getPosts()

        // then
        assertEquals(expectedPosts, result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = Exception::class)
    fun `getPosts should throw exception when service fails`(): Unit = runBlocking {
        // given
        val expectedException = RuntimeException("Network error")
        `when`(postsService.getPosts()).thenThrow(expectedException)

        // when
        postRepository.getPosts()

        // then - exception should be thrown
    }
}
