package edu.cnm.deepdive.celestialbodies.controller;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
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
public class FullTest {

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
  public void fullTest() {
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

    ViewInteraction bottomNavigationItemView2 = onView(
        allOf(withId(R.id.navigation_capture), withContentDescription("Capture"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView2.perform(click());

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.bn_capture),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        1)),
                1),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.bn_info),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        1)),
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
                    2),
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

    ViewInteraction bottomNavigationItemView3 = onView(
        allOf(withId(R.id.navigation_dashboard), withContentDescription("Dashboard"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                0),
            isDisplayed()));
    bottomNavigationItemView3.perform(click());

    ViewInteraction appCompatButton5 = onView(
        allOf(withId(R.id.stars_button), withText("Stars"),
            childAtPosition(
                allOf(withId(R.id.fragment_info),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                1),
            isDisplayed()));
    appCompatButton5.perform(click());

    ViewInteraction appCompatButton6 = onView(
        allOf(withId(R.id.planets_button), withText("Planets"),
            childAtPosition(
                allOf(withId(R.id.fragment_info),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                2),
            isDisplayed()));
    appCompatButton6.perform(click());

    ViewInteraction appCompatButton7 = onView(
        allOf(withId(R.id.meteor_button), withText("Meteor Showers"),
            childAtPosition(
                allOf(withId(R.id.fragment_info),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                3),
            isDisplayed()));
    appCompatButton7.perform(click());

    ViewInteraction appCompatButton8 = onView(
        allOf(withId(R.id.comets_button), withText("Comets"),
            childAtPosition(
                allOf(withId(R.id.fragment_info),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                4),
            isDisplayed()));
    appCompatButton8.perform(click());

    ViewInteraction appCompatButton9 = onView(
        allOf(withId(R.id.cancel_button),
            childAtPosition(
                allOf(withId(R.id.fragment_info),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                5),
            isDisplayed()));
    appCompatButton9.perform(click());

    ViewInteraction bottomNavigationItemView4 = onView(
        allOf(withId(R.id.navigation_search), withContentDescription("Search"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView4.perform(click());

    ViewInteraction appCompatButton10 = onView(
        allOf(withId(R.id.search_button),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                1),
            isDisplayed()));
    appCompatButton10.perform(click());

    ViewInteraction appCompatButton11 = onView(
        allOf(withId(R.id.dialogButtonOK), withText(" Ok "),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    appCompatButton11.perform(click());

    ViewInteraction appCompatButton12 = onView(
        allOf(withId(R.id.cancel_search), withText("Cancel"),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                2),
            isDisplayed()));
    appCompatButton12.perform(click());

    ViewInteraction actionMenuItemView = onView(
        allOf(withId(R.id.action_history), withContentDescription("History"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.action_bar),
                    1),
                0),
            isDisplayed()));
    actionMenuItemView.perform(click());

    ViewInteraction appCompatButton13 = onView(
        allOf(withId(R.id.cancel_history),
            childAtPosition(
                childAtPosition(
                    withId(R.id.fragment_container),
                    0),
                1),
            isDisplayed()));
    appCompatButton13.perform(click());

    ViewInteraction bottomNavigationItemView5 = onView(
        allOf(withId(R.id.navigation_capture), withContentDescription("Capture"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView5.perform(click());

    ViewInteraction appCompatButton14 = onView(
        allOf(withId(R.id.bn_capture),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                1),
            isDisplayed()));
    appCompatButton14.perform(click());

    ViewInteraction appCompatButton15 = onView(
        allOf(withId(R.id.bn_capture),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                1),
            isDisplayed()));
    appCompatButton15.perform(click());

    ViewInteraction appCompatButton16 = onView(
        allOf(withId(R.id.bn_info),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        0)),
                2),
            isDisplayed()));
    appCompatButton16.perform(click());

    ViewInteraction bottomNavigationItemView6 = onView(
        allOf(withId(R.id.navigation_dashboard), withContentDescription("Dashboard"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                0),
            isDisplayed()));
    bottomNavigationItemView6.perform(click());

    ViewInteraction bottomNavigationItemView7 = onView(
        allOf(withId(R.id.navigation_search), withContentDescription("Search"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView7.perform(click());

    ViewInteraction appCompatButton17 = onView(
        allOf(withId(R.id.search_button),
            childAtPosition(
                allOf(withId(R.id.fragment_search),
                    childAtPosition(
                        withId(R.id.fragment_container),
                        3)),
                1),
            isDisplayed()));
    appCompatButton17.perform(click());

    ViewInteraction appCompatButton18 = onView(
        allOf(withId(R.id.dialogButtonOK), withText(" Ok "),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    appCompatButton18.perform(click());

    ViewInteraction actionMenuItemView2 = onView(
        allOf(withId(R.id.action_history), withContentDescription("History"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.action_bar),
                    1),
                0),
            isDisplayed()));
    actionMenuItemView2.perform(click());

    ViewInteraction constraintLayout2 = onView(
        allOf(withId(R.id.activity_main),
            childAtPosition(
                allOf(withId(android.R.id.content),
                    childAtPosition(
                        withId(R.id.decor_content_parent),
                        0)),
                0),
            isDisplayed()));
    constraintLayout2.perform(click());

    ViewInteraction bottomNavigationItemView8 = onView(
        allOf(withId(R.id.navigation_dashboard), withContentDescription("Dashboard"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                0),
            isDisplayed()));
    bottomNavigationItemView8.perform(click());

    ViewInteraction constraintLayout3 = onView(
        allOf(withId(R.id.activity_main),
            childAtPosition(
                allOf(withId(android.R.id.content),
                    childAtPosition(
                        withId(R.id.decor_content_parent),
                        0)),
                0),
            isDisplayed()));
    constraintLayout3.perform(click());

    ViewInteraction bottomNavigationItemView9 = onView(
        allOf(withId(R.id.navigation_capture), withContentDescription("Capture"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                1),
            isDisplayed()));
    bottomNavigationItemView9.perform(click());

    ViewInteraction constraintLayout4 = onView(
        allOf(withId(R.id.activity_main),
            childAtPosition(
                allOf(withId(android.R.id.content),
                    childAtPosition(
                        withId(R.id.decor_content_parent),
                        0)),
                0),
            isDisplayed()));
    constraintLayout4.perform(click());

    ViewInteraction bottomNavigationItemView10 = onView(
        allOf(withId(R.id.navigation_search), withContentDescription("Search"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.navigation),
                    0),
                2),
            isDisplayed()));
    bottomNavigationItemView10.perform(click());

    ViewInteraction constraintLayout5 = onView(
        allOf(withId(R.id.activity_main),
            childAtPosition(
                allOf(withId(android.R.id.content),
                    childAtPosition(
                        withId(R.id.decor_content_parent),
                        0)),
                0),
            isDisplayed()));
    constraintLayout5.perform(click());

    ViewInteraction actionMenuItemView3 = onView(
        allOf(withId(R.id.action_history), withContentDescription("History"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.action_bar),
                    1),
                0),
            isDisplayed()));
    actionMenuItemView3.perform(click());

    ViewInteraction constraintLayout6 = onView(
        allOf(withId(R.id.activity_main),
            childAtPosition(
                allOf(withId(android.R.id.content),
                    childAtPosition(
                        withId(R.id.decor_content_parent),
                        0)),
                0),
            isDisplayed()));
    constraintLayout6.perform(click());

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    ViewInteraction appCompatTextView = onView(
        allOf(withId(R.id.title), withText("Sign out"),
            childAtPosition(
                childAtPosition(
                    withId(R.id.content),
                    0),
                0),
            isDisplayed()));
    appCompatTextView.perform(click());
  }
}
