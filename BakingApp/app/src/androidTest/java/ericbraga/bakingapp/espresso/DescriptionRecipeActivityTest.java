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

import org.hamcrest.core.AllOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.app.MockApp;
import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.environment.activities.StepInformationActivity;
import ericbraga.bakingapp.injectors.RecipeInteractorModuleIdlingResourceModule;

@RunWith(AndroidJUnit4.class)
public class DescriptionRecipeActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule =
        new IntentsTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        MockApp mockApp = (MockApp) InstrumentationRegistry.getInstrumentation()
            .getTargetContext().getApplicationContext();

        mockApp.setRecipeInteractorModule(new RecipeInteractorModuleIdlingResourceModule());
        mActivityTestRule.launchActivity(null);

        Espresso.onView(
            ViewMatchers.withId(R.id.baking_list)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click())
        );
    }

    @Test
    public void click_on_step_should_open_activity() {
        Espresso.onView(
            ViewMatchers.withId(R.id.description_ingredients_title)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.description_steps_preview)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click())
        );

        Context context = InstrumentationRegistry.getTargetContext();

        Intents.intended(
            AllOf.allOf(
                IntentMatchers.hasComponent(
                    new ComponentName(
                        context,
                        StepInformationActivity.class
                    )
                ),
                IntentMatchers.hasExtraWithKey("steps"),
                IntentMatchers.hasExtraWithKey("step_selected")
            )
        );
    }

    @After
    public void tearDown() {
        mActivityTestRule.finishActivity();
    }

}