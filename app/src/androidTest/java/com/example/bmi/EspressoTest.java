package com.example.bmi;

import org.junit.Test;
import org.junit.Rule;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class EspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkMetricSystem() {
        onView(withId(R.id.metric))
                .perform(click());
        onView(withId(R.id.massTextView))
                .check(matches(withText(R.string.massM)));
        onView(withId(R.id.heightTextView))
                .check(matches(withText(R.string.heightM)));

        onView(withId(R.id.imperial))
                .perform(click());
        onView(withId(R.id.massTextView))
                .check(matches(withText(R.string.massI)));
        onView(withId(R.id.heightTextView))
                .check(matches(withText(R.string.heightI)));
    }

    @Test
    public void checkAuthorActivityStart() {
        onView(withId(R.id.author))
                .perform(click());

        onView(withId(R.id.authorTextView))
            .check(matches(isDisplayed()));
    }
}
