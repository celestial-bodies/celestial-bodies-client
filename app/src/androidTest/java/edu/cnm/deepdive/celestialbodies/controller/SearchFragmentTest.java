package edu.cnm.deepdive.celestialbodies.controller;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import edu.cnm.deepdive.celestialbodies.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchFragmentTest {

  @Rule
  public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(
      LoginActivity.class);

  private static Matcher<View> childAtPosition(
      final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
            && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }

  @Test
  public void searchFragmentTest() {
    ViewInteraction je = onView(
        allOf(withText("Sign in"),
            childAtPosition(
                allOf(withId(R.id.sign_in),
                    childAtPosition(
                        withClassName(is("android.support.constraint.ConstraintLayout")),
                        0)),
                0),
            isDisplayed()));
    je.perform(click());

    ViewInteraction bottomNavigationItemView = onView(
        allOf(withId(R.id.navigation_search), withContentDescription("Search"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView.perform(click());

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.search_button),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                1),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.dialogButtonOK), withText(" Ok "),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    appCompatButton2.perform(click());

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(R.id.cancel_search), withText("Cancel"),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                2),
            isDisplayed()));
    appCompatButton3.perform(click());
  }
}
