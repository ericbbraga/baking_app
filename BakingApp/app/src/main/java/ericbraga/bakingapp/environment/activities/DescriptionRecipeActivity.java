package ericbraga.bakingapp.environment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.application.App;
import ericbraga.bakingapp.environment.fragments.DescriptionFragment;
import ericbraga.bakingapp.environment.fragments.EmptyFragment;
import ericbraga.bakingapp.environment.fragments.StepFragmentHelper;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionRecipeActivity extends AppCompatActivity implements
        DescriptionRecipeContract.Router {

    public static final String RECIPE_BUNBLE_KEY = "recipe";

    @Inject
    DescriptionRecipeContract.Presenter mPresenter;

    private Fragment mCurrentDetailFragment;
    private DescriptionFragment mDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent it = getIntent();
        Recipe recipe = null;

        if (it != null) {
            Bundle bundle = it.getExtras();
            if (bundle != null) {
                recipe = (Recipe) bundle.get(RECIPE_BUNBLE_KEY);
            }
        }

        ((App) getApplication()).inject(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        configurePresenter(recipe);
        configureViews();
    }

    private void configurePresenter(Recipe recipe) {
        mPresenter.setRecipe(recipe);
        mPresenter.setRouter(this);
    }

    private void configureViews() {
        setContentView(R.layout.description_recipe_activity);
        configureFragments();
    }

    private void configureFragments() {
        configureMasterDescriptionFragment();
        configureDetailFragment();
        updateFragment();
    }

    private void configureMasterDescriptionFragment() {
        mDescriptionFragment = new DescriptionFragment();
        mDescriptionFragment.setPresenter(mPresenter);
    }

    private void configureDetailFragment() {
        if (shouldShowDetailFragment()) {
            if (mCurrentDetailFragment == null) {
                mCurrentDetailFragment = new EmptyFragment();
            }
        }
    }

    @Override
    public void showMoreStepInfo(List<Step> steps, int position) {
        if (shouldShowDetailFragment()) {
            StepFragmentHelper helper = new StepFragmentHelper(this, steps, position);
            mCurrentDetailFragment = helper.createStepFragment();
            updateFragment();

        } else {
            Intent it = new Intent(this, StepInformationActivity.class);
            it.putExtra(StepInformationActivity.STEPS_BUNBLE_KEY, new ArrayList<>(steps));
            it.putExtra(StepInformationActivity.STEP_SELECTED_BUNBLE_KEY, position);
            startActivity(it);
        }
    }

    private void updateFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.master_description_part, mDescriptionFragment);

        if (shouldShowDetailFragment()) {
            transaction.replace(R.id.detail_description_part, mCurrentDetailFragment);
        }

        transaction.commit();
    }

    private boolean shouldShowDetailFragment() {
        return findViewById(R.id.detail_description_part) != null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
