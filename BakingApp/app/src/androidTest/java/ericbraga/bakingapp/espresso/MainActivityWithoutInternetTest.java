package ericbraga.bakingapp.espresso;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.app.MockApp;
import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.injectors.AsyncReadRepositoryModuleEmptyRecipes;
import ericbraga.bakingapp.injectors.NetworkModuleWithInvalidConnection;

@RunWith(AndroidJUnit4.class)
public class MainActivityWithoutInternetTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        MockApp mockApp = (MockApp) InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();

        mockApp.setNetworkModule(new NetworkModuleWithInvalidConnection(context));
        mockApp.setAsyncReadRepositoryModule(new AsyncReadRepositoryModuleEmptyRecipes(context));
        mActivityTestRule.launchActivity(null);
    }

    @Test
    public void check_if_list_not_shown() {
        requiredAssertion();
    }

    @Test
    public void check_if_list_not_shown_after_rotate() {
        mActivityTestRule.getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        );

        requiredAssertion();

        mActivityTestRule.getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        );

        requiredAssertion();
    }

    private void requiredAssertion() {
        Espresso.onView(
                ViewMatchers.withId(R.id.baking_no_elements)
        ).check(
                ViewAssertions.matches(
                        ViewMatchers.isDisplayed()
                )
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.baking_list)
        ).check(
                ViewAssertions.matches(
                        Matchers.not(
                                ViewMatchers.isDisplayed()
                        )
                )
        );
    }

    @After
    public void tearDown() {
        mActivityTestRule.finishActivity();
    }
}