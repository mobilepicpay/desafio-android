package com.picpay.desafio.android.data.utils

import com.picpay.desafio.android.base.BaseTest
import junit.framework.TestCase
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CacheTest : BaseTest() {
    lateinit var cache: CacheInterface

    companion object {
        const val KEY_1 = "key_string_1"
        const val KEY_2 = "key_string_2"
        const val ITEM_1 = "1"
        const val ITEM_2 = "2"
    }

    override fun setUp() {
        super.setUp()
        cache = Cache()
    }

    @Test
    fun testCacheWhenInitShouldBeZero() {
        assertTrue(cache.size == 0)
    }

    @Test
    fun testCacheWhenSetItemShouldReturnItemAdded() {
        cache.set(KEY_1, ITEM_1)
        assertTrue(cache.size == 1)
        assertTrue(cache.get(KEY_1)  ==  ITEM_1)
    }

    @Test
    fun testCacheWhenRemoveItemShouldDeleteItem() {
        cache.set(KEY_1, ITEM_1)
        cache.set(KEY_2, ITEM_2)
        assertTrue(cache.size == 2)
        val valueDelete = cache.remove(KEY_1)
        assertTrue(valueDelete == ITEM_1)
        assertTrue(cache.size == 1)
        assertNull(cache.get(KEY_1))
        assertTrue(cache.get(KEY_2) == ITEM_2)
    }
    @Test
    fun testCacheWhenClearShouldBeEmpty(){
        cache.set(KEY_1, ITEM_1)
        cache.set(KEY_2, ITEM_2)
        assertTrue(cache.size == 2)
        cache.clear()
        assertTrue(cache.size == 0)
    }
}