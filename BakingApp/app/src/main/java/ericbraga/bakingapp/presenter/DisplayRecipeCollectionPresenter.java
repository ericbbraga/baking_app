package ericbraga.bakingapp.presenter;

import android.media.MediaRouter;

import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;

public class DisplayRecipeCollectionPresenter implements DisplayRecipesContract.Presenter, RecipeInteractor.Callback {
    private final DisplayRecipesContract.Router mRouter;
    private DisplayRecipesContract.View mView;

    private RecipeInteractor mInteractor;

    public DisplayRecipeCollectionPresenter(DisplayRecipesContract.Router router,
                                            RecipeInteractor interactor) {
        mRouter = router;
        mInteractor = interactor;
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
        mInteractor.load(this);
    }

    @Override
    public void onPause() {
    }

    @Override
    public void recipeChosen(Recipe recipe) {
        mRouter.displayNextScreen(recipe);
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
    public void onError(String message) {
        if (mView != null) {
            mView.showError(message);
        }
    }
}
