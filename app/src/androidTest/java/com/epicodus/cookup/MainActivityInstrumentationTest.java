package com.epicodus.cookup;

import android.support.test.rule.ActivityTestRule;

import com.epicodus.cookup.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Lynn on 9/11/2017.
 */

public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateAboutActivity() {
        onView(withId(R.id.aboutButton)).perform(click());
        onView(withId(R.id.titleTextView)).check(matches
                (withText("About CookUp")));
        closeSoftKeyboard();
    }

    @Test
    public void validateEditText() {
        onView(withId(R.id.ingredientEditText)).perform(typeText("chicken"))
                .check(matches(withText("chicken")));
        closeSoftKeyboard();
    }

    @Test
    public void ingredientIsSentToRecipesActivity() {
        String ingredient = "chicken";
        onView(withId(R.id.ingredientEditText)).perform(typeText(ingredient), closeSoftKeyboard());
        onView(withId(R.id.findRecipesButton)).perform(click());
        onView(withId(R.id.ingredientTextView)).check(matches
                (withText("Recipes with " + ingredient)));
    }
}
