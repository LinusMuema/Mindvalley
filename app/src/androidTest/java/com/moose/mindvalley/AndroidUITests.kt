package com.moose.mindvalley

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.moose.mindvalley.ui.MainActivity
import junit.framework.Assert.*
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AndroidUITests {

    @get:Rule
    val mainActivity = ActivityTestRule(MainActivity::class.java, true, true)

    lateinit var episodesRecycler: RecyclerView
    lateinit var channelsRecycler: RecyclerView
    lateinit var categoriesRecycler: RecyclerView

    @Before
    fun init() {
        episodesRecycler = mainActivity.activity.findViewById(R.id.episodes_recycler)
        channelsRecycler = mainActivity.activity.findViewById(R.id.channels_recycler)
        categoriesRecycler = mainActivity.activity.findViewById(R.id.categories_recycler)
    }

    @Test
    fun checkAllTexViewsExist() {
        onView(withId(R.id.page_title)).check(matches(not(doesNotExist())))
        onView(withId(R.id.episodes_title)).check(matches(not(doesNotExist())))
        onView(withId(R.id.channels_title)).check(matches(not(doesNotExist())))
        onView(withId(R.id.categories_title)).check(matches(not(doesNotExist())))
        mainActivity.finishActivity()
    }

    @Test
    fun testRecyclerviewScroll() {
        val episodesCount = episodesRecycler.adapter!!.itemCount
        val channelsCount = channelsRecycler.adapter!!.itemCount
        onView(withId(R.id.episodes_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                episodesCount
            )
        )
        onView(withId(R.id.channels_recycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                channelsCount
            )
        )
        mainActivity.finishActivity()
    }

    @Test
    fun testRecyclerViewItems() {
        onView(withId(R.id.episodes_recycler)).check(itemViewMatches(1, R.id.episode_title, withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.episodes_recycler)).check(itemViewMatches(1, R.id.episode_image, withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.channels_recycler)).check(itemViewMatches(1, R.id.channel_name, withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.channels_recycler)).check(itemViewMatches(1, R.id.channel_icon, withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.categories_recycler)).check(itemViewMatches(1, R.id.category_title, withEffectiveVisibility(Visibility.VISIBLE)))
        mainActivity.finishActivity()
    }


    private fun itemViewMatches(position: Int, @IdRes resId: Int, viewMatcher: Matcher<View>): ViewAssertion {
        assertNotNull(viewMatcher)

        return ViewAssertion { view, noViewException ->
            if (noViewException != null) {
                throw noViewException
            }

            assertTrue("View is RecyclerView", view is RecyclerView)

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            val itemType = adapter!!.getItemViewType(position)
            val viewHolder = adapter.createViewHolder(recyclerView, itemType)
            adapter.bindViewHolder(viewHolder, position)

            val targetView = if (resId == -1) {
                viewHolder.itemView
            } else {
                viewHolder.itemView.findViewById(resId)
            }

            if (viewMatcher.matches(targetView)) {
                return@ViewAssertion  // Found a matching view
            }

            fail("No match found")
        }
    }
}
