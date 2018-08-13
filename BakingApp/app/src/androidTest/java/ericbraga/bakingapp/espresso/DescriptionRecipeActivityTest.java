package ericbraga.bakingapp.espresso;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
import ericbraga.bakingapp.environment.activities.DescriptionRecipeActivity;
import ericbraga.bakingapp.environment.activities.StepInformationActivity;
import ericbraga.bakingapp.espresso.util.RecipeMockFactory;
import ericbraga.bakingapp.model.Recipe;

@RunWith(AndroidJUnit4.class)
public class DescriptionRecipeActivityTest {

    @Rule
    public IntentsTestRule<DescriptionRecipeActivity> mActivityTestRule =
        new IntentsTestRule<>(DescriptionRecipeActivity.class, true, false);

    @Before
    public void setUp() {
        Recipe recipe = RecipeMockFactory.createMockRecipe();

        Intent it = new Intent();
        it.putExtra(DescriptionRecipeActivity.RECIPE_BUNBLE_KEY, recipe);

        mActivityTestRule.launchActivity(it);
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
                IntentMatchers.hasExtraWithKey(StepInformationActivity.STEPS_BUNBLE_KEY),
                IntentMatchers.hasExtraWithKey(StepInformationActivity.STEP_SELECTED_BUNBLE_KEY)
            )
        );
    }

    @After
    public void tearDown() {
        mActivityTestRule.finishActivity();
    }

}