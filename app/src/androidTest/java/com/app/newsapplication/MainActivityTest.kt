package com.app.newsapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.app.newsapplication.views.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get:Rule
    val activityScenario = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun allNewsbtnClick() {
        //Delayinnng the button click
        runBlocking {
            launch {
                //Button Click
                onView(withId(R.id.btnAllNews)).perform(click())
            }
            delay(5000)
        }
    }

    @Test
    fun isNewsFragmentVisibleAtLaunch() {

        //News Fragment Visible Click
        onView(withId(R.id.fragment_news_parent)).check(matches(isDisplayed()))
    }
}