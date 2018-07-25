package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.interactor.interfaces.ExternalMediaContract;
import ericbraga.bakingapp.interactor.interfaces.StepContentInteractor;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DisplayStepContract;

public class StepPresenter<T> implements DisplayStepContract.Presenter<T>,
        StepContentInteractor.Callback<T>,ExternalMediaContract.Callback {
    private final StepContentInteractor<T> mStepContentInteractor;
    private final List<Step> mSteps;
    private ExternalMediaContract mMediaController;
    private int mCurrentStepIndex;
    private Step mCurrentStep;
    private DisplayStepContract.View<T> mView;
    private boolean mPlaying;

    public StepPresenter(StepContentInteractor<T> interactor,
                         ExternalMediaContract mediaController,
                         List<Step> steps,
                         int selectedPosition) {
        mStepContentInteractor = interactor;
        mMediaController = mediaController;
        mSteps = steps;
        mCurrentStepIndex = selectedPosition;
        mPlaying = false;
    }

    @Override
    public void attachView(DisplayStepContract.View<T> view) {
        mView = view;
    }

    @Override
    public void onResume() {
        loadStepContent();
    }

    @Override
    public void nextStepWidgetClicked() {
        if (hasNextStep()) {
            mCurrentStepIndex = mCurrentStepIndex + 1;
            loadStepContent();
        }
    }

    @Override
    public void previousStepWidgetClicked() {
        if (hasPreviousStep()) {
            mCurrentStepIndex = mCurrentStepIndex - 1;
            loadStepContent();
        }
    }

    @Override
    public void detach() {
        if (mPlaying) {
            mMediaController.stop();
        }

        mMediaController.release();
        mView = null;
    }

    private void loadStepContent() {
        mCurrentStep = mSteps.get(mCurrentStepIndex);
        mStepContentInteractor.loadInternalStepInformation(mCurrentStep, this);
    }

    @Override
    public void onSuccessLoadThumbnail(T image) {
        if (mView != null) {
            mView.displayPreview(image);
            mView.hideVideo();
            displayCommonInformation();
        }

    }

    @Override
    public void onSuccessLoadVideo(String url) {
        if (mMediaController != null) {
            mMediaController.prepare(url);
            mMediaController.setCallback(this);
        }

        if (mView != null) {
            mView.displayVideo();
            mView.hidePreview();
            displayCommonInformation();
        }
    }

    @Override
    public void onSourceInvalid() {
        if (mView != null) {
            mView.removeAllRecipePreviewElements();
            displayCommonInformation();
        }
    }

    private void displayCommonInformation() {
        if (mView != null) {
            mView.displayShortDescription(mCurrentStep.getShortDescription());
            mView.displayDescription(mCurrentStep.getDescription());

            toggleNextWidget();
            togglePreviousWidget();
        }
    }

    private void togglePreviousWidget() {
        if (mView != null) {
            if (hasPreviousStep()) {
                mView.enablePreviousWidget();
            } else {
                mView.disablePreviousWidget();
            }
        }
    }

    private void toggleNextWidget() {
        if (mView != null) {
            if (hasNextStep()) {
                mView.enableNextWidget();
            } else {
                mView.disableNextWidget();
            }
        }

    }

    private boolean hasNextStep() {
        return mCurrentStepIndex < mSteps.size() - 1;
    }

    private boolean hasPreviousStep() {
        return mCurrentStepIndex > 0;
    }

    @Override
    public void onError(String message) {
        // TODO: Send error message
    }

    @Override
    public void onMediaPlayerChangedState(boolean playing) {
        if (mView != null) {

            mPlaying = playing;

            if (mPlaying) {
                mView.disableNextWidget();
                mView.disablePreviousWidget();

            } else {
                togglePreviousWidget();
                toggleNextWidget();
            }
        }

    }
}
