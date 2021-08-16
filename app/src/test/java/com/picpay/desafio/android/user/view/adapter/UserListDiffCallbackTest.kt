package com.picpay.desafio.android.user.view.adapter

import com.picpay.desafio.android.user.domain.UserDomain
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserListDiffCallbackTest {

    @Test
    fun `Should return are items the same as true when items id are equals`() {
        val oldList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))
        val newList = listOf(UserDomain(imageUrl = "img2", name = "name2", id = 1, userName = "username2"))

        val diffCallback = UserListDiffCallback(oldList, newList)

        assertTrue(diffCallback.areItemsTheSame(oldItemPosition = 0, newItemPosition = 0))
    }

    @Test
    fun `Should return are items the same as false when items id are different`() {
        val oldList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))
        val newList = listOf(UserDomain(imageUrl = "img", name = "name", id = 2, userName = "username"))

        val diffCallback = UserListDiffCallback(oldList, newList)

        assertFalse(diffCallback.areItemsTheSame(oldItemPosition = 0, newItemPosition = 0))
    }

    @Test
    fun `Should return are contents the same as true when items are equals`() {
        val oldList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))
        val newList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))

        val diffCallback = UserListDiffCallback(oldList, newList)

        assertTrue(diffCallback.areContentsTheSame(oldItemPosition = 0, newItemPosition = 0))
    }

    @Test
    fun `Should return are contents the same as false when items are different`() {
        val oldList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))
        val newList = listOf(UserDomain(imageUrl = "img2", name = "name2", id = 1, userName = "username2"))

        val diffCallback = UserListDiffCallback(oldList, newList)

        assertFalse(diffCallback.areContentsTheSame(oldItemPosition = 0, newItemPosition = 0))
    }

    @Test
    fun `Should return correctly old list size when get old list size is called`() {
        val oldList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))
        val newList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))

        val diffCallback = UserListDiffCallback(oldList, newList)

        assertEquals(diffCallback.oldListSize, 1)
    }

    @Test
    fun `Should return correctly new list size when get new list size is called`() {
        val oldList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))
        val newList = listOf(UserDomain(imageUrl = "img", name = "name", id = 1, userName = "username"))

        val diffCallback = UserListDiffCallback(oldList, newList)

        assertEquals(diffCallback.newListSize, 1)
    }
}