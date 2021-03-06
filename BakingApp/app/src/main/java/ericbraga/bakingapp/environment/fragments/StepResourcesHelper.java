package ericbraga.bakingapp.environment.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.controllers.AndroidPlayer;
import ericbraga.bakingapp.environment.interfaces.PlayerViewContract;
import ericbraga.bakingapp.environment.repositories.image.GlideLoader;
import ericbraga.bakingapp.interactor.implementation.LoadStepContent;
import ericbraga.bakingapp.interactor.implementation.LoadStepsFurtherInfo;
import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.interfaces.StepContentInteractor;
import ericbraga.bakingapp.interactor.interfaces.StepInteractor;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.StepPresenter;

public class StepResourcesHelper {

    private final Context mContext;
    private final List<Step> mSteps;
    private final int mSelectedPosition;
    private final boolean mPlaying;
    private final long mCurrentPosition;
    private AndroidPlayer mAndroidPlayer;
    private StepPresenter<Drawable> mPresenter;

    public StepResourcesHelper(Context context, List<Step> steps, int selectedPosition) {
        this(context, steps, selectedPosition, false, 0);
    }

    public StepResourcesHelper(Context context, List<Step> steps, int selectedPosition,
                               boolean playing, long currentPosition) {
        mContext = context;
        mSteps = steps;
        mSelectedPosition = selectedPosition;
        mPlaying = playing;
        mCurrentPosition = currentPosition;

        configureAndroidPlayer();
        configurePresenter(mSteps);
    }

    public PlayerViewContract getPlayerViewContract() {
        return mAndroidPlayer;
    }

    public StepPresenter<Drawable> getPresenter() {
        return mPresenter;
    }

    private void configureAndroidPlayer() {
        String applicationName = mContext.getResources().getString(R.string.app_name);
        mAndroidPlayer = new AndroidPlayer(mContext, applicationName, mPlaying, mCurrentPosition);
    }

    private void configurePresenter(List<Step> steps) {
        ImageRepository<Drawable> repository = new GlideLoader(mContext);
        StepInteractor<Drawable> stepInteractor = new LoadStepContent<>(repository);
        StepContentInteractor<Drawable> stepContentInteractor =
                new LoadStepsFurtherInfo<>(stepInteractor);

        mPresenter = new StepPresenter<>(stepContentInteractor, mAndroidPlayer, steps,
                mSelectedPosition);
    }
}
