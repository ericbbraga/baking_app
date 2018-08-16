package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.presenter.interfaces.ExternalMediaContract;
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

    public StepPresenter(StepContentInteractor<T> interactor,
                         ExternalMediaContract mediaController,
                         List<Step> steps,
                         int selectedPosition) {
        mStepContentInteractor = interactor;

        mMediaController = mediaController;
        mMediaController.setCallback(this);

        mSteps = steps;
        mCurrentStepIndex = selectedPosition;
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
    public int getCurrentStepIndex() {
        return mCurrentStepIndex;
    }

    @Override
    public void nextStepWidgetClicked() {
        if (hasNextStep()) {
            setCurrentStepIndex(mCurrentStepIndex + 1);
        }
    }

    @Override
    public void previousStepWidgetClicked() {
        if (hasPreviousStep()) {
            setCurrentStepIndex(mCurrentStepIndex - 1);
        }
    }

    @Override
    public void setCurrentStepIndex(int stepPosition) {
        if (stepPosition >= 0 && stepPosition <= mSteps.size()) {
            mCurrentStepIndex = stepPosition;
            mMediaController.reset();
            loadStepContent();
        }
    }

    @Override
    public void detach() {
        if (mMediaController != null) {
            if (mMediaController.isPlaying()) {
                mMediaController.stop();
            }

            mMediaController.release();
            mMediaController = null;
        }

        mView = null;
    }

    @Override
    public long getCurrentPosition() {
        return mMediaController.getCurrentPosition();
    }

    @Override
    public boolean isPlaying() {
        return mMediaController != null && mMediaController.isPlaying();

    }

    private void loadStepContent() {
        mCurrentStep = mSteps.get(mCurrentStepIndex);
        mStepContentInteractor.execute(mCurrentStep, this);
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
    public void onMediaPlayerChangedState() {
        if (mView != null) {
            if (mMediaController.isPlaying()) {
                mView.disableNextWidget();
                mView.disablePreviousWidget();

            } else {
                togglePreviousWidget();
                toggleNextWidget();
            }
        }

    }
}
