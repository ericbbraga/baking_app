package ericbraga.bakingapp.espresso;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.activities.StepInformationActivity;
import ericbraga.bakingapp.espresso.util.StepMockFactory;
import ericbraga.bakingapp.model.Step;

@RunWith(AndroidJUnit4.class)
public class StepsInformationActivityTest {

    @Rule
    public IntentsTestRule<StepInformationActivity> mActivityTestRule =
        new IntentsTestRule<>(StepInformationActivity.class, true, false);
    private List<Step> mSteps;
    private Intent mIntent;

    @Before
    public void setUp() {
        mSteps = StepMockFactory.createMockSteps();
        mIntent = new Intent();
        mIntent.putExtra(StepInformationActivity.STEPS_BUNBLE_KEY, new ArrayList<>(mSteps));
    }

    @Test
    public void first_step_should_shown_previous_button_disabled() {
        mIntent.putExtra(StepInformationActivity.STEP_SELECTED_BUNBLE_KEY, 0);
        mActivityTestRule.launchActivity(mIntent);

        Espresso.onView(
            ViewMatchers.withId(R.id.step_information_previous_step)
        ).check(
            ViewAssertions.matches(
                Matchers.not(
                    ViewMatchers.isEnabled()
                )
            )
        );

        mActivityTestRule.finishActivity();
    }

    @Test
    public void any_step_between_first_last_should_show_all_buttons() {
        for (int stepIndex = 1; stepIndex < mSteps.size() - 1; stepIndex++) {
            mIntent.putExtra(StepInformationActivity.STEP_SELECTED_BUNBLE_KEY, stepIndex);
            mActivityTestRule.launchActivity(mIntent);

            Espresso.onView(
                ViewMatchers.withId(R.id.step_information_previous_step)
            ).check(
                ViewAssertions.matches(
                    ViewMatchers.isEnabled()
                )
            );

            Espresso.onView(
                ViewMatchers.withId(R.id.step_information_next_step)
            ).check(
                ViewAssertions.matches(
                    ViewMatchers.isEnabled()
                )
            );

            mActivityTestRule.finishActivity();
        }
    }

    @Test
    public void last_step_should_shown_next_button_disabled() {
        final int lastIndex = mSteps.size() - 1;
        mIntent.putExtra(StepInformationActivity.STEP_SELECTED_BUNBLE_KEY, lastIndex);
        mActivityTestRule.launchActivity(mIntent);

        Espresso.onView(
            ViewMatchers.withId(R.id.step_information_next_step)
        ).check(
            ViewAssertions.matches(
                Matchers.not(
                    ViewMatchers.isEnabled()
                )
            )
        );
    }

    @After
    public void tearDown() {
        mSteps = null;
        mIntent = null;
    }

}
