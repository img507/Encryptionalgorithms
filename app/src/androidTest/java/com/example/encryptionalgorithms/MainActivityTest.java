package com.example.encryptionalgorithms;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCaesarButton_opensCaesarActivity() {
        onView(withId(R.id.btnCaesar)).perform(click());
        onView(withId(R.id.encryptButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testAESButton_opensAESActivity() {
        onView(withId(R.id.btnAES)).perform(click());
        onView(withId(R.id.encryptButtonAES)).check(matches(isDisplayed()));
    }

    @Test
    public void testDESButton_opensDESActivity() {
        onView(withId(R.id.btnDES)).perform(click());
        onView(withId(R.id.encryptButtonDES)).check(matches(isDisplayed()));
    }

    @Test
    public void testRSAButton_opensRSAActivity() {
        onView(withId(R.id.btnRSA)).perform(click());
        onView(withId(R.id.generateKeysButtonRSA)).check(matches(isDisplayed()));
    }

    @Test
    public void testSHA256Button_opensSHA256Activity() {
        onView(withId(R.id.btnSHA256)).perform(click());
        onView(withId(R.id.calculateHashButtonSHA)).check(matches(isDisplayed()));
    }
}