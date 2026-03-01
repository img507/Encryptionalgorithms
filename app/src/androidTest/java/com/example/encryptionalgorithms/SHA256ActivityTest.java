package com.example.encryptionalgorithms;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class SHA256ActivityTest {

    @Rule
    public ActivityScenarioRule<SHA256Activity> activityRule =
            new ActivityScenarioRule<>(SHA256Activity.class);

    @Test
    public void testHashCalculationAndVerification_WithSameText() {
        String originalText = "Espresso Test";
        String differentText = "Espresso test";
        String knownHash = "151cc2b32c473f7f371b6503d3877380638bfccc3ec14fccc5a5dccf3f332c9a";

        onView(withId(R.id.inputTextSHA)).perform(typeText(originalText), closeSoftKeyboard());
        onView(withId(R.id.calculateHashButtonSHA)).perform(click());
        onView(withId(R.id.hashResultTextSHA)).check(matches(withText("Хеш (SHA-256):\n" + knownHash)));

        onView(withId(R.id.textToVerifySHA)).perform(replaceText(originalText), closeSoftKeyboard());
        onView(withId(R.id.verifyButtonSHA)).perform(click());
        onView(withId(R.id.verificationResultTextSHA)).check(matches(withText("Хеши СОВПАДАЮТ")));

        onView(withId(R.id.textToVerifySHA)).perform(replaceText(differentText), closeSoftKeyboard());
        onView(withId(R.id.verifyButtonSHA)).perform(click());
        onView(withId(R.id.verificationResultTextSHA)).check(matches(withText("Хеши НЕ СОВПАДАЮТ")));
    }
}