package com.example.sally.rxjavakotlin.ui.home


import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.sally.rxjavakotlin.R
import com.example.sally.rxjavakotlin.ui.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



/**
 * Created by Sally Salem on 2/5/18.
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val repositoriesActivityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java, true, false)

    @Before
    fun setUp() {
        Intents.init();
        val intent = Intent()
        repositoriesActivityRule.launchActivity(intent)
    }

    @After
    fun tearDown() {
        Intents.release();
    }
//
//    @Test
//    fun getRepositoriesList_Invalid() { // empty state
//        val intent = Intent()
//        repositoriesActivityRule.launchActivity(intent)
//
//        Espresso.onView(ViewMatchers.withId(R.id.swipe_container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        Espresso.onView(ViewMatchers.withId(R.id.sp_repository_sort_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
////        Espresso.onView(ViewMatchers.withId(R.id.tv_empty_data)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
////        Espresso.onView(ViewMatchers.withId(R.id.tv_empty_data)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.no_data_available)))
//
////        Espresso.onView(ViewMatchers.withId(R.id.rv_repositories_list)).check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())));
////        Espresso.onView(ViewMatchers.withId(R.id.rv_repositories_list)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
//    }

    @Test
    fun getRepositoriesList_valid() { // not empty
        Espresso.onView(ViewMatchers.withId(R.id.swipe_container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_empty_data)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        Espresso.onView(ViewMatchers.withId(R.id.sp_repository_sort_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_repositories_list)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    fun repositoriesList_CheckValueAtPosition() {

        val intent = Intent()
        repositoriesActivityRule.launchActivity(intent)

        Espresso.onView(ViewMatchers.withId(R.id.rv_repositories_list))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(".dotfiles"))))
    }

    @Test
    fun repositoriesList_CheckValueAtSpecialPosition() {
        val intent = Intent()
        repositoriesActivityRule.launchActivity(intent)

        //special position
        Espresso.onView(
                CoreMatchers.allOf(ViewMatchers.withId(R.id.tv_repository_name),
                        childAtPosition(
                                CoreMatchers.allOf(ViewMatchers.withId(R.id.rl_item_container),
                                        childAtPosition(
                                                ViewMatchers.withId(R.id.rv_repositories_list),
                                                3)),
                                0),
                        ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.withText("CodeMirror")))


    }

    @Test
    fun repositoriesList_CheckValueAtInvisiblePosition() {
        val intent = Intent()
        repositoriesActivityRule.launchActivity(intent)

        //Special position using RecyclerViewActions
        Espresso.onView(ViewMatchers.withId(R.id.rv_repositories_list)).perform(RecyclerViewActions.scrollToPosition<RepositoryAdapter.RepositoryItemViewHolder>(9))
        Espresso.onView(
                CoreMatchers.allOf(ViewMatchers.withId(R.id.tv_repository_name), ViewMatchers.withText("Flickr"), // we can set tag and test it withTagValue
                        ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.withText("Flickr")))

        val recyclerView = repositoriesActivityRule.activity.rv_repositories_list as RecyclerView
        assertTrue(recyclerView.adapter.itemCount > 0)
    }

    @Test
    fun navigateToDetailsScreen() {
        val intent = Intent()
        repositoriesActivityRule.launchActivity(intent)

        Espresso.onView(
                CoreMatchers.allOf(ViewMatchers.withId(R.id.rv_repositories_list),
                        childAtPosition(
                                CoreMatchers.allOf(ViewMatchers.withId(R.id.swipe_container),
                                        childAtPosition(
                                                ViewMatchers.withClassName(CoreMatchers.`is`("android.widget.LinearLayout")),
                                                0)),
                                0),
                        ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_repositories_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        Intents.intended(IntentMatchers.hasComponent(DetailsActivity::class.java.name))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
