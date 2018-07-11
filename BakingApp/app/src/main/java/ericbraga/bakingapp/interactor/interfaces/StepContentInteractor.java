package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Step;

public interface StepContentInteractor<T> {
    void loadInternalStepInformation(Step step, StepContentInteractor.Callback<T> callback);

    interface Callback<T> {
        void onSuccessLoadThumbnail(T image);
        void onSuccessLoadVideo(String url);
        void onSourceInvalid();
        void onError(String message);
    }
}
