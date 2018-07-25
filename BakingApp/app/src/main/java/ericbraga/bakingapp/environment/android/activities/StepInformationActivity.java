package ericbraga.bakingapp.environment.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.android.fragments.StepFragmentHelper;
import ericbraga.bakingapp.environment.android.fragments.StepInformationFragment;
import ericbraga.bakingapp.model.Step;

public class StepInformationActivity extends AppCompatActivity {
    private StepInformationFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent it = getIntent();

        List<Step> steps = it.getParcelableArrayListExtra("steps");
        final int selected = it.getIntExtra("step_selected", 0);

        configureViews(steps, selected);
        addFragmentIntoScreen();
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
