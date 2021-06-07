package com.example.magicsquare;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameTests extends ActivityTestRule<GameActivity>{
    private static String TAG = GameTests.class.getSimpleName();

    @Rule
    public ActivityTestRule<GameActivity> rule = new ActivityTestRule<>(GameActivity.class);
    private GameActivity mActivity;

    public GameTests() {
        super(GameActivity.class);
    }

    // Проверка доступности кнопок
    @Test
    public void testElementsEnabled() {
        onView(withId(R.id.submit)).check(matches(isEnabled()));
        onView(withId(R.id.newgame)).check(matches(isEnabled()));
        onView(withId(R.id.resume)).check(matches(isEnabled()));
        onView(withId(R.id.exit)).check(matches(isEnabled()));
        onView(withId(R.id.help)).check(matches(isEnabled()));
    }

    // Проверка доступности полей ввода
    @Test
    public void testEditTextDisplayed() {
        onView(withId(R.id.res_0)).check(matches(isDisplayed()));
        onView(withId(R.id.res_1)).check(matches(isDisplayed()));
        onView(withId(R.id.res_2)).check(matches(isDisplayed()));
        onView(withId(R.id.res_3)).check(matches(isDisplayed()));
        onView(withId(R.id.res_4)).check(matches(isDisplayed()));
        onView(withId(R.id.res_5)).check(matches(isDisplayed()));
        onView(withId(R.id.res_6)).check(matches(isDisplayed()));
        onView(withId(R.id.res_7)).check(matches(isDisplayed()));
        onView(withId(R.id.res_8)).check(matches(isDisplayed()));
    }


    // Проверка работы кнопок
    @Test
    public void testButtonClicks() {
        onView(withId(R.id.newgame)).perform(click());
        onView(withId(R.id.help)).perform(click());
        onView(withId(R.id.submit)).perform(click());
    }


    // Фокус на поле ввода и скрытие клавиатуры
    @Test
    public void testTypeTextCloseSoftKeyboard() {
        onView(withId(R.id.res_0)).perform(click());
        closeSoftKeyboard();
    }


}