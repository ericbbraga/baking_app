package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.FavoriteRecipeInteractor;
import ericbraga.bakingapp.interactor.interfaces.RecipeFavoriteInteractor;
import ericbraga.bakingapp.interactor.interfaces.UpdateStatusInteractor;
import ericbraga.bakingapp.model.Recipe;

public class ChangeRecipeFavorite implements RecipeFavoriteInteractor {
    private final UpdateStatusInteractor mChangeFavoriteRecipeStatus;
    private final FavoriteRecipeInteractor mGetFavoriteRecipe;

    public ChangeRecipeFavorite(UpdateStatusInteractor changeFavoriteRecipeStatus,
                                FavoriteRecipeInteractor getFavoriteRecipe) {
        mChangeFavoriteRecipeStatus = changeFavoriteRecipeStatus;
        mGetFavoriteRecipe = getFavoriteRecipe;
    }

    @Override
    public void execute(final Recipe favoriteRecipe,
                        final RecipeFavoriteInteractor.Callback callback) {
        mGetFavoriteRecipe.execute(new FavoriteRecipeInteractor.Callback() {
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
