package es.guiguegon.espresso.testing.example;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by guillermoguerrero on 15/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class, true,     // initialTouchMode
                    true);   // launchActivity

    @Test
    public void testLoginWrong() {
        onView(withId(R.id.email)).perform(typeText("email@dominio.com"));
        onView(withId(R.id.password)).perform(replaceText("contraseña"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withId(R.id.password)).check(
                matches(hasErrorText(activityRule.getActivity().getString(R.string.error_incorrect_password))));
    }

    @Test
    public void testLoginSuccess() {
        Intents.init();
        onView(withId(R.id.email)).perform(typeText("foo@example.com"));
        onView(withId(R.id.password)).perform(replaceText("hello"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
        Intents.release();
    }
}
