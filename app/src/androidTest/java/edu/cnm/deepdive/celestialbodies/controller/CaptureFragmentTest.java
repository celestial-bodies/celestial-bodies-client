package edu.cnm.deepdive.celestialbodies.controller;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.support.test.espresso.DataInteraction;
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
public class CaptureFragmentTest {

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
  public void captureFragmentTest() {
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
        allOf(withId(R.id.navigation_capture), withContentDescription("Capture"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView.perform(click());

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.bn_capture),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                1),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.bn_info),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                2),
            isDisplayed()));
    appCompatButton2.perform(click());

    DataInteraction linearLayout = onData(anything())
        .inAdapterView(allOf(withId(R.id.list_info),
            childAtPosition(
                withClassName(is("android.support.constraint.ConstraintLayout")),
                0)))
        .atPosition(1);
    linearLayout.perform(click());

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(R.id.infoButtonOK), withText(" Ok "),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    appCompatButton3.perform(click());

    ViewInteraction appCompatButton4 = onView(
        allOf(withId(R.id.cancel_button_info),
            childAtPosition(
                childAtPosition(
                    withId(R.id.fragment_container),
                    1),
                1),
            isDisplayed()));
    appCompatButton4.perform(click());

    ViewInteraction constraintLayout = onView(
        allOf(withId(R.id.activity_main),
            childAtPosition(
                allOf(withId(android.R.id.content),
                    childAtPosition(
                        withId(R.id.decor_content_parent),
                        0)),
                0),
            isDisplayed()));
    constraintLayout.perform(click());
  }
}
