package com.example.magicsquare;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainTests extends ActivityTestRule<MainActivity> {
    private static String TAG = MainTests.class.getSimpleName();

    private MainActivity mActivity;

    public MainTests() {
        super(MainActivity.class);
    }


    // Проверка существования элементов
    @Test
    public void testElementDoesNotExists() {
        onView(withId(R.id.buttonRules)).check(doesNotExist());
        onView(withId(R.id.buttonNew)).check(doesNotExist());
        onView(withId(R.id.buttonAdv)).check(doesNotExist());
        onView(withId(R.id.buttonExp)).check(doesNotExist());
        onView(withId(R.id.hello)).check(doesNotExist());
    }

    // Проверка размещения кнопок
    @Test
    public void testPositions() {
        onView(withId(R.id.buttonRules)).check(isCompletelyLeftOf(withId(R.id.buttonNew)));
        onView(withId(R.id.buttonAdv)).check(isCompletelyLeftOf(withId(R.id.buttonExp)));
        onView(withId(R.id.buttonAdv)).check(isCompletelyBelow(withId(R.id.buttonRules)));
        onView(withId(R.id.buttonExp)).check(isCompletelyBelow(withId(R.id.buttonNew)));
    }

    // Проверка доступности кнопок
    @Test
    public void testIsEnabled() {
        onView(withId(R.id.buttonRules)).check(matches(isClickable()));
        onView(withId(R.id.buttonNew)).check(matches(isClickable()));
        onView(withId(R.id.buttonAdv)).check(matches(isClickable()));
        onView(withId(R.id.buttonExp)).check(matches(isClickable()));
    }

}