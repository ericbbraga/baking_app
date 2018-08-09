package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.GetFavoriteRecipeInteractor;
import ericbraga.bakingapp.interactor.interfaces.ChangeRecipeFavoriteInteractor;
import ericbraga.bakingapp.interactor.interfaces.UpdateStatusInteractor;
import ericbraga.bakingapp.model.Recipe;

public class ChangeRecipeFavorite implements ChangeRecipeFavoriteInteractor {
    private final UpdateStatusInteractor mChangeFavoriteRecipeStatus;
    private final GetFavoriteRecipeInteractor mGetFavoriteRecipe;

    public ChangeRecipeFavorite(UpdateStatusInteractor changeFavoriteRecipeStatus,
                                GetFavoriteRecipeInteractor getFavoriteRecipe) {
        mChangeFavoriteRecipeStatus = changeFavoriteRecipeStatus;
        mGetFavoriteRecipe = getFavoriteRecipe;
    }

    @Override
    public void execute(final Recipe favoriteRecipe,
                        final ChangeRecipeFavoriteInteractor.Callback callback) {
        mGetFavoriteRecipe.execute(new GetFavoriteRecipeInteractor.Callback() {
            @Override
            public void favoriteRecipe(Recipe recipe) {
                favoriteRecipe.setStarred(true);
                mChangeFavoriteRecipeStatus.execute(favoriteRecipe);

                if (recipe != null) {
                    recipe.setStarred(false);
                    mChangeFavoriteRecipeStatus.execute(recipe);
                    callback.onUpdateRecipe(recipe);
                }
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
