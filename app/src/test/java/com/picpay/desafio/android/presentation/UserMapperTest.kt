package com.picpay.desafio.android.presentation

import com.desafio.picpay.android.testcoreutil.ResourceFileReader.parseJson
import com.picpay.desafio.android.ui.presentation.UserMapper
import com.picpay.desafio.android.ui.presentation.UserViewObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserMapperTest {

    companion object {

        private var mappedObject: List<UserViewObject>? = null

        @BeforeClass
        @JvmStatic
        fun setUp() {
            val mapper = UserMapper()
            mappedObject =
                mapper.responseToViewObject(parseJson("users/mock_list_user.json"))
        }
    }

    @Test
    fun `given a response object when the mapper is called must return a view object`() {
        assertEquals(mappedObject?.get(0)?.name, "Tom")
        assertEquals(mappedObject?.get(0)?.userName, "Cruise")
        assertNotNull(mappedObject?.get(0)?.imageProfile)
    }
}