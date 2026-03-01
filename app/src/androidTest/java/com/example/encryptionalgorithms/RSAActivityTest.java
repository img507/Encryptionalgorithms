package com.example.encryptionalgorithms;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.startsWith;


@RunWith(AndroidJUnit4.class)
public class RSAActivityTest {

    @Rule
    public ActivityScenarioRule<RSAActivity> activityRule =
            new ActivityScenarioRule<>(RSAActivity.class);

    @Test
    public void testFullWorkflow_Success() {
        String originalText = "RSA asymmetric test";

        onView(withId(R.id.generateKeysButtonRSA)).perform(click());

        onView(withId(R.id.publicKeyTextRSA)).check(matches(withText(startsWith("Открытый ключ:\n"))));
        onView(withId(R.id.privateKeyTextRSA)).check(matches(withText(startsWith("Закрытый ключ:\n"))));

        onView(withId(R.id.inputTextRSA)).perform(typeText(originalText), closeSoftKeyboard());

        onView(withId(R.id.encryptButtonRSA)).perform(scrollTo(), click());

        onView(withId(R.id.decryptButtonRSA)).perform(scrollTo(), click());

        onView(withId(R.id.decryptedResultTextRSA)).perform(scrollTo())
                .check(matches(withText("Расшифрованный текст: " + originalText)));

        onView(withId(R.id.backButtonRSA)).perform(scrollTo(), click());
    }
}