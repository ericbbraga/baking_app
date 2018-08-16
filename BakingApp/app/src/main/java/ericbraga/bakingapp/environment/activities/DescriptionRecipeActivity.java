package ericbraga.bakingapp.environment.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import ericbraga.bakingapp.environment.fragments.StepResourcesHelper;
import ericbraga.bakingapp.environment.fragments.StepInformationFragment;
import ericbraga.bakingapp.environment.interfaces.PlayerViewContract;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.StepPresenter;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionRecipeActivity extends AppCompatActivity implements
        DescriptionRecipeContract.Router {

    public static final String RECIPE_BUNBLE_KEY = "recipe";
    private static final String STEP_SELECTED_BUNBLE_KEY = "step_selected";
    private static final String STEP_CURRENT_POSITION = "step_current";
    private static final String STEP_PLAYING = "playing";

    @Inject
    DescriptionRecipeContract.Presenter mPresenter;

    private EmptyFragment mEmptyFragment;
    private StepInformationFragment mStepInformationFragment;
    private DescriptionFragment mDescriptionFragment;

    private boolean mShowStepFragment;

    private Recipe mRecipe;
    private int mCurrentStepIndex;
    private boolean mPlaying;
    private long mCurrentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent it = getIntent();

        if (it != null) {
            Bundle bundle = it.getExtras();
            if (bundle != null) {
                mRecipe = (Recipe) bundle.get(RECIPE_BUNBLE_KEY);
            }
        }

        ((App) getApplication()).inject(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mEmptyFragment = new EmptyFragment();

        if (savedInstanceState != null) {
            mShowStepFragment = true;
            mCurrentStepIndex = savedInstanceState.getInt(STEP_SELECTED_BUNBLE_KEY);
            mCurrentPosition = savedInstanceState.getLong(STEP_CURRENT_POSITION);
            mPlaying = savedInstanceState.getBoolean(STEP_PLAYING);

            mPresenter.setStepSelect(mCurrentStepIndex);

        } else {
            mShowStepFragment = false;
            mCurrentStepIndex = 0;
            mCurrentPosition = 0;
            mPlaying = false;
        }

        configurePresenter();
        configureViews();
    }

    private void configurePresenter() {
        mPresenter.setRecipe(mRecipe);
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
        if (shouldShowDetailFragment() && shouldShowStepFragment())  {
            FragmentManager manager = getSupportFragmentManager();
            mStepInformationFragment = (StepInformationFragment)
                manager.findFragmentById(R.id.detail_description_part);

            StepResourcesHelper helper = new StepResourcesHelper(this, mRecipe.getSteps(),
                mCurrentStepIndex, mPlaying, mCurrentPosition);

            StepPresenter<Drawable> presenter = helper.getPresenter();
            PlayerViewContract playerViewContract = helper.getPlayerViewContract();

            mStepInformationFragment.setPlayerViewContract(playerViewContract);
            mStepInformationFragment.setPresenter(presenter);
        }
    }

    @Override
    public void showMoreStepInfo(List<Step> steps, int position) {
        if (shouldShowDetailFragment()) {
            mShowStepFragment = true;

            if (mStepInformationFragment == null) {
                StepResourcesHelper helper = new StepResourcesHelper(this, steps, position);

                StepPresenter<Drawable> presenter = helper.getPresenter();
                PlayerViewContract playerViewContract = helper.getPlayerViewContract();

                mStepInformationFragment = new StepInformationFragment();
                mStepInformationFragment.setPlayerViewContract(playerViewContract);
                mStepInformationFragment.setPresenter(presenter);

            } else {
                mStepInformationFragment.setStepPosition(position);
            }

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
            if (shouldShowStepFragment()) {
                transaction.replace(R.id.detail_description_part, mStepInformationFragment);
            } else {
                transaction.replace(R.id.detail_description_part, mEmptyFragment);
            }
        }

        transaction.commit();
    }

    private boolean shouldShowDetailFragment() {
        return findViewById(R.id.detail_description_part) != null;
    }

    private boolean shouldShowStepFragment() {
        return mShowStepFragment;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mStepInformationFragment != null) {
            outState.putInt(STEP_SELECTED_BUNBLE_KEY, mStepInformationFragment.getCurrentIndex());
            outState.putLong(STEP_CURRENT_POSITION, mStepInformationFragment.getCurrentPosition());
            outState.putBoolean(STEP_PLAYING, mStepInformationFragment.isPlaying());
        }
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
