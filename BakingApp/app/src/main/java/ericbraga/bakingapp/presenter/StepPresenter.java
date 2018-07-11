package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.interactor.interfaces.MediaInteractor;
import ericbraga.bakingapp.interactor.interfaces.StepContentInteractor;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DisplayStepContract;

public class StepPresenter<T> implements DisplayStepContract.Presenter<T>,
        StepContentInteractor.Callback<T> {
    private final StepContentInteractor<T> mStepContentInteractor;
    private final List<Step> mSteps;
    private int mCurrentStepIndex;
    private Step mCurrentStep;
    private DisplayStepContract.View<T> mView;

    public StepPresenter(StepContentInteractor<T> interactor, MediaInteractor mediaInteractor,
                         List<Step> steps) {
        mStepContentInteractor = interactor;
        mSteps = steps;
        mCurrentStepIndex = 0;
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
        mCurrentStepIndex = mCurrentStepIndex + 1;
        loadStepContent();
    }

    @Override
    public void previousStepWidgetClicked() {
        mCurrentStepIndex = mCurrentStepIndex - 1;
        loadStepContent();
    }

    private void loadStepContent() {
        mCurrentStep = mSteps.get(mCurrentStepIndex);
        mStepContentInteractor.loadInternalStepInformation(mCurrentStep, this);
    }

    @Override
    public void onSuccessLoadThumbnail(T image) {
        mView.displayPreview(image);
        mView.hideVideo();
        displayCommonInformation();
    }

    @Override
    public void onSuccessLoadVideo(String url) {
        mView.displayVideo(url);
        mView.hidePreview();
        displayCommonInformation();
    }

    @Override
    public void onSourceInvalid() {
        mView.displayDefaultImage();
        mView.hideVideo();
        displayCommonInformation();
    }

    private void displayCommonInformation() {
        mView.displayShortDescription(mCurrentStep.getShortDescription());
        mView.displayDescription(mCurrentStep.getDescription());
    }

    @Override
    public void onError(String message) {

    }
}
