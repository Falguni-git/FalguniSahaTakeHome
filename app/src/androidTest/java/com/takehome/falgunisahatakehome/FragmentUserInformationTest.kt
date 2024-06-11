package com.takehome.falgunisahatakehome

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.takehome.falgunisahatakehome.view.MainActivity
import com.takehome.falgunisahatakehome.view.MainActivity.Companion.INFORMATION_SCREEN_TAG
import com.takehome.falgunisahatakehome.view.screens.FragmentUserInformationScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FragmentUserInformationTest{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun testSearchButtonIsDisplayed() {
        onView(withText("SEARCH")).check(matches(isDisplayed()))
    }

    @Test fun testSearchButtonOnClick(){
        onView(withId(R.id.searchButton))
            .perform(click())
    }

    @Test fun testSearchText(){
        onView(withId(R.id.searchText))
            .check(matches(withHint("Enter a github user id")))
    }
}