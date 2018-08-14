package ericbraga.bakingapp.espresso;

import android.content.ComponentName;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.app.MockApp;
import ericbraga.bakingapp.environment.activities.DescriptionRecipeActivity;
import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.injectors.RecipeInteractorModuleIdlingResourceModule;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule =
        new IntentsTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        /**
         * @see MockApp
         * @see RecipeInteractorModuleIdlingResourceModule
         * I'm testing the real app here. However,
         * I named the 'MockApp' object because a need to introduce IdlingResources
         * (setRecipeInteractorModule) to wait for Interation with other
         * threads (please check MockApp.java class).
         *
         * I've decided use this approach since I don't like to change my production code to
         * introduce IdlingResource code (please check the
         * RecipeInteractorModuleIdlingResourceModule class)
         * */
        MockApp mockApp = (MockApp) InstrumentationRegistry.getInstrumentation()
            .getTargetContext().getApplicationContext();

        mockApp.setRecipeInteractorModule(new RecipeInteractorModuleIdlingResourceModule());
        mActivityTestRule.launchActivity(null);
    }

    @Test
    public void check_if_recipes_were_loaded() {
        Context context = InstrumentationRegistry.getTargetContext();
        requiredAssertion();

        Espresso.onView(
            ViewMatchers.withId(R.id.baking_list)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click())
        );

        Intents.intended(
            AllOf.allOf(
                IntentMatchers.hasComponent(
                    new ComponentName(
                        context,
                        DescriptionRecipeActivity.class
                    )
                ),
                IntentMatchers.hasExtraWithKey(DescriptionRecipeActivity.RECIPE_BUNBLE_KEY)
            )
        );
    }

    private void requiredAssertion() {
        Espresso.onView(
            ViewMatchers.withId(R.id.baking_list)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.baking_no_elements)
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
