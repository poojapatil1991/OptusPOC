package com.example.optusdemo.userInfo.view


import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.optusdemo.R
import com.example.optusdemo.albumList.view.AlbumListActivity
import kotlinx.android.synthetic.main.content_user_info.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.*
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(AlbumListActivity::class.java)
    lateinit var mAlbumListActivity: AlbumListActivity

    @Before
    fun setUp() {
        mAlbumListActivity = mActivityTestRule.activity
    }

    @Test
    fun onCreate() {
        var rvUserList = mAlbumListActivity.rv_user_list
        Assert.assertNotNull(rvUserList)
    }

    @Test
    fun testSwipeRefresh(){
        onView(withId(R.id.swipe_refresh)).perform(ViewActions.swipeDown())
    }

    @Test
    fun testRecyclerViewScrollUp(){
        onView(withId(R.id.rv_user_list)).perform(ViewActions.swipeUp())
    }

    @Test
    fun testRecyclerViewScrollDown(){
        onView(withId(R.id.rv_user_list)).perform(ViewActions.swipeDown())
    }

    @Test
    fun testRecyclerViewScrolling(){
        onView(withId(R.id.rv_user_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testActionBarTitleDisplay(){
        val actionBar: ActionBar?= mAlbumListActivity.supportActionBar
        Assert.assertNotNull(actionBar!!.title)
    }

    @After
    fun tearDown() {
    }
    @Test
    fun albumListActivityTest2() {
        val recyclerView = onView(
            allOf(
                withId(R.id.rv_user_list),
                childAtPosition(
                    withId(R.id.swipe_refresh),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val imageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.user_info_toolbar_title),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val viewGroup = onView(
            allOf(
                withId(R.id.swipe_refresh),
                childAtPosition(
                    allOf(
                        withId(R.id.content_layout),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.rv_user_list),
                childAtPosition(
                    allOf(
                        withId(R.id.swipe_refresh),
                        childAtPosition(
                            withId(R.id.content_layout),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        recyclerView2.check(matches(isDisplayed()))

        val imageView = onView(
            allOf(
                withId(R.id.iv_photo),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.tv_name),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))
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
