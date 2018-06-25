package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.environment.common.interfaces.Repository;
import ericbraga.bakingapp.interactor.RecipeInteractor;
import ericbraga.bakingapp.model.RecipeCollection;

public class LoadRecipes implements RecipeInteractor, Repository.RepositoryCallback {

    private final Repository mRepository;
    private final RecipeInteractor.Callback mCallback;

    public LoadRecipes(Repository repository, RecipeInteractor.Callback callback) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository could not be null");
        }

        mRepository = repository;
        mCallback = callback;
    }

    @Override
    public void load() {
        mRepository.listRecipes(this);
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
