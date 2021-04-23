package com.picpay.desafio.android.feature_contacts.presentation


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.picpay.desafio.android.feature_contacts.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserActTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(UserActivity::class.java)

    @Test
    fun userActTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val recyclerView3 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val recyclerView4 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        val textInputEditText = onView(
            allOf(
                withId(R.id.queryTextInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("leonardo"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.queryTextInput), withText("leonardo"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(pressImeActionButton())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.queryTextInput), withText("leonardo"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(click())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.queryTextInput), withText("leonardo"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("ed"))

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.queryTextInput), withText("ed"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(closeSoftKeyboard())

        val recyclerView5 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.queryTextInput), withText("ed"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(replaceText("ma"))

        val textInputEditText7 = onView(
            allOf(
                withId(R.id.queryTextInput), withText("ma"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText7.perform(closeSoftKeyboard())

        val recyclerView6 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView6.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textInputEditText8 = onView(
            allOf(
                withId(R.id.queryTextInput), withText("ma"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText8.perform(replaceText(""))

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.queryTextInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText9.perform(closeSoftKeyboard())

        val recyclerView7 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView7.perform(actionOnItemAtPosition<ViewHolder>(8, click()))

        val textInputEditText10 = onView(
            allOf(
                withId(R.id.queryTextInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText10.perform(pressImeActionButton())

        val recyclerView8 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView8.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        val recyclerView9 = onView(
            allOf(
                withId(R.id.usersRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView9.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        val textInputEditText11 = onView(
            allOf(
                withId(R.id.queryTextInput),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rootLayoutTextInput),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText11.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
