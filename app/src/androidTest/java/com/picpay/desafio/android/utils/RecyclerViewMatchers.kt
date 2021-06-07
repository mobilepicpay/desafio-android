package com.picpay.desafio.android.utils

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withRecyclerView(recyclerViewId: Int) = RecyclerViewMatchers(recyclerViewId)

class RecyclerViewMatchers(private val recyclerViewId: Int, var position: Int = -1) {

    fun atPosition(position: Int) = atPositionOnView(position, -1)

    fun atPositionOnView(position: Int, targetViewId: Int) = object : TypeSafeMatcher<View>() {
        var resources: Resources? = null
        var childView: View? = null

        override fun describeTo(description: Description) {
            var idDescription = Integer.toString(recyclerViewId)
            resources?.let {
                idDescription = try {
                    it.getResourceName(recyclerViewId)
                } catch (ex: Resources.NotFoundException) {
                    String.format(
                        "%s (resource name not found)",
                        arrayOf<Any>(Integer.valueOf(recyclerViewId))
                    )
                }
            }

            description.appendText("with id: $idDescription")
        }

        override fun matchesSafely(view: View): Boolean {

            resources = view.resources
            if (childView == null) {
                val recyclerView =
                    view.rootView.findViewById<RecyclerView>(recyclerViewId)
                if (recyclerView.id == recyclerViewId) {
                    childView =
                        recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                } else {
                    return false
                }
            }

            return if (targetViewId == -1) {
                view === childView
            } else {
                val targetView = childView?.findViewById<View>(targetViewId)
                view === targetView
            }
        }
    }
}