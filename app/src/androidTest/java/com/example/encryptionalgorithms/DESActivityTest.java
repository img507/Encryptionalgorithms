package com.example.encryptionalgorithms;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.startsWith;


@RunWith(AndroidJUnit4.class)
public class DESActivityTest {

    @Rule
    public ActivityScenarioRule<DESActivity> activityRule =
            new ActivityScenarioRule<>(DESActivity.class);

    @Test
    public void testEncryptDecrypt_Success() {
        String originalText = "This is a secret message";
        String key = "MyKeys11";

        onView(withId(R.id.inputTextDES)).perform(typeText(originalText), closeSoftKeyboard());

        onView(withId(R.id.keyTextDES)).perform(typeText(key), closeSoftKeyboard());

        onView(withId(R.id.encryptButtonDES)).perform(click());

        onView(withId(R.id.encryptedResultTextDES)).check(matches(withText(startsWith("Зашифрованный текст (Base64):\n"))));

        onView(withId(R.id.decryptButtonDES)).perform(click());

        onView(withId(R.id.decryptedResultTextDES)).check(matches(withText("Расшифрованный текст: " + originalText)));
    }
}