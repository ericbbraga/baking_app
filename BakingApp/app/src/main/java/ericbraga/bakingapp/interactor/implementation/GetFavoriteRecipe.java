package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.interactor.interfaces.FavoriteRecipeInteractor;
import ericbraga.bakingapp.model.Recipe;

public class GetFavoriteRecipe implements FavoriteRecipeInteractor {
    private final AsyncReadRepository mReadRepository;

    public GetFavoriteRecipe(AsyncReadRepository readRepository) {
        mReadRepository = readRepository;
    }

    @Override
    public void execute(final Callback callback) {
        if (callback != null) {
            mReadRepository.getStarredRecipe(
                    new AsyncReadRepository.RepositoryCallbackStarredRecipe() {
                @Override
                public void starredRecipe(Recipe recipe) {
                    callback.favoriteRecipe(recipe);
                }

                @Override
                public void errorMessage(String message) {
                    callback.onError(message);
                }
            });
        }
    }
}
