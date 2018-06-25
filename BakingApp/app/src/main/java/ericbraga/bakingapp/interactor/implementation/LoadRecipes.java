package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.boundary.RecipeCollection;
import ericbraga.bakingapp.environment.common.WebRecipeCollection;
import ericbraga.bakingapp.environment.common.interfaces.Repository;
import ericbraga.bakingapp.environment.common.repositories.AbstractRecipeRepository;
import ericbraga.bakingapp.interactor.RecipeInteractor;

public class LoadRecipes implements RecipeInteractor, Repository.RepositoryCallback {

    private final AbstractRecipeRepository mRespository;
    private final RecipeInteractor.Callback mCallback;

    public LoadRecipes(AbstractRecipeRepository repository, RecipeInteractor.Callback callback) {
        mRespository = repository;
        mCallback = callback;
    }

    @Override
    public void load() {
        mRespository.listRecipes(this);
    }

    @Override
    public void listRecipesContent(RecipeCollection recipes) {
        if (mCallback != null) {
            mCallback.onResultReceive(recipes);
        }
    }

    @Override
    public void errorMessage(String message) {
        // TODO: Implements Error
        if (mCallback != null) {
            mCallback.onError(message);
        }
    }
}
