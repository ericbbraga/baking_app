package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Step;

public interface StepInteractor<T> {
    void execute(Step step, StepInteractor.Callback<T> callback);

    interface Callback<T> {
        void onSuccess(T image);
        void displayFallbackImage();
        void onError(String message);
    }
}
