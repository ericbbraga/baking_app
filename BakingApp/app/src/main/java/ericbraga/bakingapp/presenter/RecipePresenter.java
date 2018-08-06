package ericbraga.bakingapp.presenter;

import ericbraga.bakingapp.interactor.interfaces.RecipeDisplayInteractor;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.presenter.interfaces.RecipeContract;

public class RecipePresenter<T> implements RecipeContract.Presenter<T>,
        RecipeDisplayInteractor.Callback<T> {
    private final Recipe mRecipe;
    private final RecipeDisplayInteractor<T> mInteractor;
    private RecipeContract.View<T> mView;

    public RecipePresenter(RecipeDisplayInteractor<T> interactor, Recipe recipe) {
        mInteractor = interactor;
        mRecipe = recipe;
    }

    @Override
    public void attachView(RecipeContract.View<T> view) {
        mView = view;
        loadImage();
    }

    @Override
    public void detachView() {
        mView = null;
    }

    private void loadImage() {
        mView.showProgress();
        mInteractor.execute(mRecipe, this);
    }

    @Override
    public void onSuccess(T image) {
        mView.display(image);
        mView.setTitleRecipe(mRecipe.getName());
        mView.configureStarred(mRecipe.isStarred());
        mView.hideProgress();
        detachView();
    }

    @Override
    public void displayFallbackImage() {
        // TODO: Display Fallback Image
    }

    @Override
    public void onError(String message) {
        mView.showError(message);
        mView.hideProgress();
        detachView();
    }
}
