package com.example.uitests

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @Test
    fun whenFragmentCreated_verifyLoadingViewIsShowing() {
        launchFragmentInContainer<MainFragment>()
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun whenFragmentCreated_verifyTextViewIsEmpty() {
        launchFragmentInContainer<MainFragment>()
        onView(withId(R.id.text_view_name)).check(matches(withText("")))
    }

    @Test
    fun waitOneSecond_whenFragmentCreated_verifyLoadingIsNotShowing() {
        launchFragmentInContainer<MainFragment>()
        Thread.sleep(1_000)
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun waitOneSecond_whenFragmentCreated_verifyTextViewHasValue() {
        launchFragmentInContainer<MainFragment>()
        Thread.sleep(1_000)
        onView(withId(R.id.text_view_name)).check(matches(withText("Bem vindo! ")))
    }

    @Test
    fun givenArgsAndWaitOneSecond_whenFragmentCreated_verifyTextViewsText() {
        val name = "Vitor"
        val defaultText = "Bem vindo! "
        val expectedText = defaultText + name
        val args = bundleOf("TEXT_VIEW_TEXT" to name)
        launchFragmentInContainer<MainFragment>(fragmentArgs = args)
        Thread.sleep(1_000)
        onView(withId(R.id.text_view_name)).check(matches(withText(expectedText)))
    }

    @Test
    fun givenArgsWithoutNameAndWaitOneSecond_whenFragmentCreated_verifyTextViewsText() {
        val expected = "Bem vindo! "
        launchFragmentInContainer<MainFragment>()
        Thread.sleep(1_000)
        onView(withId(R.id.text_view_name)).check(matches(withText(expected)))
    }
}