package com.example.optusdemo.userInfo.view


import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
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
class UserInfoActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(UserInfoActivity::class.java)
    lateinit var mUserInfoActivity: UserInfoActivity

    @Before
    fun setUp() {
        mUserInfoActivity = mActivityTestRule.activity
    }

    @Test
    fun onCreate() {
        var rvUserList = mUserInfoActivity.rv_user_list
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
        val actionBar: ActionBar?= mUserInfoActivity.supportActionBar
        Assert.assertNotNull(actionBar!!.title)
    }

    @After
    fun tearDown() {
    }
    @Test
    fun userInfoActivityTest() {
        val viewGroup = onView(
            allOf(
                withId(R.id.toolbar),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.user_info_toolbar_title), withText("User Info"),
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
        textView.check(matches(withText("User Info")))

        val textView2 = onView(
            allOf(
                withId(R.id.tv_name), withText("Name: Leanne Graham"),
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

        val textView3 = onView(
            allOf(
                withId(R.id.tv_ID), withText("ID: 1"),
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
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.tv_email),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))

        val viewGroup2 = onView(
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
        viewGroup2.check(matches(isDisplayed()))

        val recyclerView = onView(
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
        recyclerView.check(matches(isDisplayed()))

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
