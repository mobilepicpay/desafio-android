package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.PostResponse
import com.picpay.desafio.android.domain.model.Post
import junit.framework.Assert.assertEquals
import org.junit.Test

class PostMapperTest {

    @Test
    fun `PostResponse toDomain should map correctly`() {
        // given
        val postResponse = PostResponse(
            userId = 1,
            id = 1,
            title = "Test Title",
            body = "Test Body"
        )
        val expectedPost = Post(
            userId = 1,
            id = 1,
            title = "Test Title",
            body = "Test Body"
        )

        // when
        val result = postResponse.toDomain()

        // then
        assertEquals(expectedPost, result)
    }

    @Test
    fun `List PostResponse toDomain should map all items correctly`() {
        // given
        val postResponses = listOf(
            PostResponse(1, 1, "Title 1", "Body 1"),
            PostResponse(2, 2, "Title 2", "Body 2"),
            PostResponse(3, 3, "Title 3", "Body 3")
        )
        val expectedPosts = listOf(
            Post(1, 1, "Title 1", "Body 1"),
            Post(2, 2, "Title 2", "Body 2"),
            Post(3, 3, "Title 3", "Body 3")
        )

        // when
        val result = postResponses.toDomain()

        // then
        assertEquals(expectedPosts, result)
        assertEquals(3, result.size)
    }

    @Test
    fun `empty list should map to empty list`() {
        // given
        val postResponses = emptyList<PostResponse>()
        val expectedPosts = emptyList<Post>()

        // when
        val result = postResponses.toDomain()

        // then
        assertEquals(expectedPosts, result)
        assertEquals(0, result.size)
    }
}
