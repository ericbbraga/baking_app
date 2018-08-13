package ericbraga.bakingapp.presenter.interfaces;

public interface RecipeContract<T> {

    interface Presenter<T> {
        void attachView(View<T> view);
        void detachView();
    }

    interface View<T> {
        void showProgress();
        void setTitleRecipe(String title);
        void display(T image);
        void showError(String error);
        void hideProgress();
        void configureStarred(boolean starred);
        void displayFallbackImage();
    }
}
