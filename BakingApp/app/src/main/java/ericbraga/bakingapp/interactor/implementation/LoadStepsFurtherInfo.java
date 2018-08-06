package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.StepContentInteractor;
import ericbraga.bakingapp.interactor.interfaces.StepInteractor;
import ericbraga.bakingapp.model.Step;

public class LoadStepsFurtherInfo<T> implements StepContentInteractor<T>,StepInteractor.Callback<T> {
    private final StepInteractor<T> mStepInteractor;
    private Callback<T> mOuterCallback;

    public LoadStepsFurtherInfo(StepInteractor<T> stepInteractor) {
        mStepInteractor = stepInteractor;
    }

    @Override
    public void execute(Step step, Callback<T> callback) {
        mOuterCallback = callback;

        if (step.getVideoUrl() != null && !step.getVideoUrl().isEmpty()) {
            mOuterCallback.onSuccessLoadVideo(step.getVideoUrl());
        } else  {
            mStepInteractor.execute(step, this);
        }
    }

    @Override
    public void onSuccess(T image) {
        mOuterCallback.onSuccessLoadThumbnail(image);
    }

    @Override
    public void displayFallbackImage() {
        mOuterCallback.onSourceInvalid();
    }

    @Override
    public void onError(String message) {
        mOuterCallback.onError(message);
    }
}
