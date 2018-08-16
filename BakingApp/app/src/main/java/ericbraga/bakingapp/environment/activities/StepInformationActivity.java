package ericbraga.bakingapp.environment.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.fragments.StepResourcesHelper;
import ericbraga.bakingapp.environment.fragments.StepInformationFragment;
import ericbraga.bakingapp.environment.interfaces.PlayerViewContract;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.StepPresenter;

public class StepInformationActivity extends AppCompatActivity {
    public static final String STEPS_BUNBLE_KEY = "steps";
    public static final String STEP_SELECTED_BUNBLE_KEY = "step_selected";
    private static final String STEP_CURRENT_POSITION = "step_current";
    private static final String STEP_PLAYING = "playing";
    private StepInformationFragment mFragment;
    private ArrayList<Step> mSteps;
    private int mSelected;
    private boolean mPlaying;
    private long mCurrentPosition;

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
            mPlaying = extraBundle.getBoolean(STEP_PLAYING, false);
            mCurrentPosition = extraBundle.getLong(STEP_CURRENT_POSITION, 0);
        }

        configureActionBar();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configureViews(mSteps, mSelected, mPlaying, mCurrentPosition);
            addFragmentIntoScreen();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            configureViews(mSteps, mSelected, mPlaying, mCurrentPosition);
            addFragmentIntoScreen();
        }
    }

    private void configureViews(List<Step> steps, int selected,
                                boolean playing, long currentPosition) {
        setContentView(R.layout.step_information_activity);
        StepResourcesHelper helper = new StepResourcesHelper(this, steps, selected, playing,
            currentPosition);

        StepPresenter<Drawable> presenter = helper.getPresenter();
        PlayerViewContract playerViewContract = helper.getPlayerViewContract();

        mFragment = new StepInformationFragment();
        mFragment.setPresenter(presenter);
        mFragment.setPlayerViewContract(playerViewContract);
    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.show();
            } else {
                actionBar.hide();
            }
        }
    }

    private void addFragmentIntoScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.master_step_list, mFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSelected = mFragment.getCurrentIndex();
        mPlaying = mFragment.isPlaying();
        mCurrentPosition = mFragment.getCurrentPosition();

        // TODO: This could be moved to an interactor that will save the current data
        outState.putParcelableArrayList(STEPS_BUNBLE_KEY, mSteps);
        outState.putInt(STEP_SELECTED_BUNBLE_KEY, mSelected);
        outState.putLong(STEP_CURRENT_POSITION, mCurrentPosition);
        outState.putBoolean(STEP_PLAYING, mPlaying);
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
