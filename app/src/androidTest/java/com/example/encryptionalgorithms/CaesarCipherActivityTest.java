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

@RunWith(AndroidJUnit4.class)
public class CaesarCipherActivityTest {

    @Rule
    public ActivityScenarioRule<CaesarCipherActivity> activityRule =
            new ActivityScenarioRule<>(CaesarCipherActivity.class);

    @Test
    public void testEncryptionAndDecryption_isCorrect() {
        String originalText = "Hello World";
        String key = "3";
        String encryptedText = "Khoor Zruog";

        onView(withId(R.id.inputText)).perform(typeText(originalText), closeSoftKeyboard());

        onView(withId(R.id.keyText)).perform(typeText(key), closeSoftKeyboard());

        onView(withId(R.id.encryptButton)).perform(click());

        onView(withId(R.id.resultText)).check(matches(withText("Зашифрованный текст: " + encryptedText)));

        onView(withId(R.id.decryptButton)).perform(click());

        onView(withId(R.id.resultText)).check(matches(withText("Расшифрованный текст: " + originalText)));
    }
}