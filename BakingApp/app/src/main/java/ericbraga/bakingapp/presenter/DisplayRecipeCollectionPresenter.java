package ericbraga.bakingapp.presenter;

import ericbraga.bakingapp.interactor.interfaces.ChangeRecipeFavoriteInteractor;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;

public class DisplayRecipeCollectionPresenter implements DisplayRecipesContract.Presenter,
        RecipeInteractor.Callback, ChangeRecipeFavoriteInteractor.Callback {
    private final DisplayRecipesContract.Router mRouter;
    private DisplayRecipesContract.View mView;

    private RecipeInteractor mInteractor;
    private ChangeRecipeFavoriteInteractor mChangeRecipeFavoriteInteractor;

    public DisplayRecipeCollectionPresenter(DisplayRecipesContract.Router router,
                                            RecipeInteractor interactor,
                                            ChangeRecipeFavoriteInteractor changeRecipeFavoriteInteractor) {
        mRouter = router;
        mInteractor = interactor;
        mChangeRecipeFavoriteInteractor = changeRecipeFavoriteInteractor;
    }

    @Override
    public void attachView(DisplayRecipesContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onResume() {
        mView.hideEmptyList();
        mInteractor.execute(this);
    }

    @Override
    public void onPause() {
    }

    @Override
    public void recipeChosen(Recipe recipe) {
        mRouter.displayNextScreen(recipe);
    }

    @Override
    public void favoriteItem(Recipe recipe, boolean starred) {
        mChangeRecipeFavoriteInteractor.execute(recipe, this);
    }

    @Override
    public void onResultReceive(RecipeCollection collection) {
        if (mView != null) {
            if (collection.size() == 0) {
                mView.showEmptyList();
            } else {
                mView.display(collection);
            }
        }
    }

    @Override
    public void onUpdateRecipe(Recipe recipe) {
        if (mView != null) {
            mView.updateRecipeStatus(recipe);
        }
    }

    @Override
    public void onError(String message) {
        if (mView != null) {
            mView.showError(message);
        }
    }
}
