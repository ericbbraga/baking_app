package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.Repository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.model.RecipeCollection;

public class LoadRecipes implements RecipeInteractor, Repository.RepositoryCallback {

    private final Repository mRepository;
    private RecipeInteractor.Callback mCallback;

    public LoadRecipes(Repository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository could not be null");
        }

        mRepository = repository;
    }

    @Override
    public void load(RecipeInteractor.Callback callback) {
        mCallback = callback;
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
        if (mCallback != null) {
            mCallback.onError(message);
        }
    }
}
