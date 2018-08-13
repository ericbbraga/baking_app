package ericbraga.bakingapp.environment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.fragments.StepFragmentHelper;
import ericbraga.bakingapp.environment.fragments.StepInformationFragment;
import ericbraga.bakingapp.model.Step;

public class StepInformationActivity extends AppCompatActivity {
    public static final String STEPS_BUNBLE_KEY = "steps";
    public static final String STEP_SELECTED_BUNBLE_KEY = "step_selected";
    private StepInformationFragment mFragment;
    private ArrayList<Step> mSteps;
    private int mSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent it = getIntent();

        Bundle extraBundle = it.getExtras();
        if (savedInstanceState != null) {
            extraBundle = savedInstanceState;
        }

        if (extraBundle != null) {
            mSteps = extraBundle.getParcelableArrayList(STEPS_BUNBLE_KEY);
            mSelected = extraBundle.getInt(STEP_SELECTED_BUNBLE_KEY, 0);

            configureViews(mSteps, mSelected);
        }

        addFragmentIntoScreen();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSelected = mFragment.getCurrentIndex();

        outState.putParcelableArrayList("steps", mSteps);
        outState.putInt("step_selected", mSelected);
    }

    private void configureViews(List<Step> steps, int selected) {
        setContentView(R.layout.step_information_activity);
        StepFragmentHelper helper = new StepFragmentHelper(this, steps, selected);
        mFragment = helper.createStepFragment();
    }

    private void addFragmentIntoScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.master_step_list, mFragment)
                .commit();
    }
}
