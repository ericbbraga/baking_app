package ericbraga.bakingapp.environment.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.controllers.AndroidPlayer;
import ericbraga.bakingapp.environment.repositories.image.GlideLoader;
import ericbraga.bakingapp.interactor.implementation.LoadStepContent;
import ericbraga.bakingapp.interactor.implementation.LoadStepsFurtherInfo;
import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.interfaces.StepContentInteractor;
import ericbraga.bakingapp.interactor.interfaces.StepInteractor;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.StepPresenter;

public class StepFragmentHelper {

    private final Context mContext;
    private final List<Step> mSteps;
    private final int mSelectedPosition;
    private StepInformationFragment mFragment;
    private AndroidPlayer mAndroidPlayer;
    private StepPresenter<Drawable> mPresenter;

    public StepFragmentHelper(Context context, List<Step> steps, int selectedPosition) {
        mContext = context;
        mSteps = steps;
        mSelectedPosition = selectedPosition;
    }

    public StepInformationFragment createStepFragment() {
        configureAndroidPlayer();
        configurePresenter(mSteps);
        configureFragment();

        return mFragment;
    }

    private void configureAndroidPlayer() {
        String applicationName = mContext.getResources().getString(R.string.app_name);
        mAndroidPlayer = new AndroidPlayer(mContext, applicationName);
    }

    private void configurePresenter(List<Step> steps) {
        ImageRepository<Drawable> repository = new GlideLoader(mContext);
        StepInteractor<Drawable> stepInteractor = new LoadStepContent<>(repository);
        StepContentInteractor<Drawable> stepContentInteractor =
                new LoadStepsFurtherInfo<>(stepInteractor);

        mPresenter = new StepPresenter<>(stepContentInteractor, mAndroidPlayer, steps,
                mSelectedPosition);
    }

    private void configureFragment() {
        mFragment = new StepInformationFragment();
        mFragment.setPlayerViewContract(mAndroidPlayer);
        mFragment.setPresenter(mPresenter);
    }
}
