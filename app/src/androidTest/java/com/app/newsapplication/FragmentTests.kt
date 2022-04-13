package com.app.newsapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.app.newsapplication.adapter.NewsResponseAdapter
import com.app.newsapplication.views.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentTests {

    @get:Rule
    val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    /**
     * News RecyclerView appears at the App Launch
     */
    @Test
    fun isNewsRecyclerViewVisibleOnAppLaunch() {
        onView(withId(R.id.rvNews)).check(matches(isDisplayed()))
    }

    /**
     * Selecting list item and check if the Detailed Fragment is visible
     */
    @Test
    fun selectListItem_isDetailedFragmentVisible() {
        onView(withId(R.id.rvNews))
            .perform(actionOnItemAtPosition<NewsResponseAdapter.NewsResponseViewHolder>(4, click()))

        onView(withId(R.id.fragment_detailed_parent)).check(matches(isDisplayed()))
    }

    /**
     * Select list item and check if Detailed Fragment is visisble AND
     * on Back Press from Detailed Fragment and check if the News Fragment is visible
     */
    @Test
    fun onbackPress_isNewsFragmentVisible() {
        onView(withId(R.id.rvNews))
            .perform(actionOnItemAtPosition<NewsResponseAdapter.NewsResponseViewHolder>(4, click()))
        onView(withId(R.id.fragment_detailed_parent)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.fragment_news_parent)).check(matches(isDisplayed()))
    }

}