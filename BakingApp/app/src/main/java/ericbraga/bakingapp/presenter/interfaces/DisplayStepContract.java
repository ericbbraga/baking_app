package ericbraga.bakingapp.presenter.interfaces;

public interface DisplayStepContract {
    interface Presenter<T> {
        void attachView(DisplayStepContract.View<T> view);
        void onResume();
        int getCurrentStepIndex();
        void nextStepWidgetClicked();
        void previousStepWidgetClicked();
        void setCurrentStepIndex(int stepPosition);
        void detach();
        long getCurrentPosition();
        boolean isPlaying();
    }

    interface View<T> {
        void displayShortDescription(String shortDescription);
        void displayDescription(String description);
        void displayPreview(T image);
        void displayVideo();
        void removeAllRecipePreviewElements();
        void hideVideo();
        void hidePreview();
        void enablePreviousWidget();
        void disablePreviousWidget();
        void disableNextWidget();
        void enableNextWidget();
    }
}
