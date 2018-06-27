package ericbraga.bakingapp.presenter;

import ericbraga.bakingapp.interactor.RecipeInteractor;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.mvpcontract.DisplayRecipesContract;

public class DisplayRecipesPresenter implements DisplayRecipesContract.Presenter, RecipeInteractor.Callback {
    private DisplayRecipesContract.View mView;

    private RecipeInteractor mInteractor;

    public DisplayRecipesPresenter(RecipeInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void attachView(DisplayRecipesContract.View view) {
        mView = view;
    }

    @Override
    public void detachView(DisplayRecipesContract.View view) {
        mView = null;
    }

    @Override
    public void onResume() {
        mInteractor.load(this);
    }

    @Override
    public void onPause() {
    }

    @Override
    public void recipeChosen(Recipe recipe) {
        mView.displayNextScreen(recipe);
    }

    @Override
    public void onResultReceive(RecipeCollection collection) {
        if (mView != null) {
            mView.display(collection);
        }
    }

    @Override
    public void onError(String message) {
        if (mView != null) {
            mView.showError(message);
        }
    }
}
