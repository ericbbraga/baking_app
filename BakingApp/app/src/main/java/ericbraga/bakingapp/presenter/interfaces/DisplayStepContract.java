package ericbraga.bakingapp.presenter.interfaces;

public interface DisplayStepContract {
    interface Presenter<T> {
        void attachView(DisplayStepContract.View<T> view);
        void onResume();
        void nextStepWidgetClicked();
        void previousStepWidgetClicked();
        void detach();
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
