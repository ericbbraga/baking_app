package ericbraga.bakingapp.presenter.interfaces;

public interface DisplayStepContract {
    interface Presenter<T> {
        void attachView(DisplayStepContract.View<T> view);
        void onResume();
        void nextStepWidgetClicked();
        void previousStepWidgetClicked();
    }

    interface View<T> {
        void displayShortDescription(String shortDescription);
        void displayDescription(String description);
        void displayPreview(T image);
        void displayVideo(String url);
        void displayDefaultImage();
        void hideVideo();
        void hidePreview();
        void enablePreviousWidget();
        void disablePreviousWidget();
        void disableNextWidget();
        void enableNextWidget();
    }
}
