package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.model.RecipeCollection;

public class LoadRecipes implements RecipeInteractor, AsyncReadRepository.RepositoryCallback {

    private final AsyncReadRepository mReadRepository;
    private RecipeInteractor.Callback mCallback;

    public LoadRecipes(AsyncReadRepository read) {
        if (read == null) {
            throw new IllegalArgumentException("AsyncReadRepository could not be null");
        }

        mReadRepository = read;
    }

    @Override
    public void execute(RecipeInteractor.Callback callback) {
        mCallback = callback;
        mReadRepository.listRecipes(this);
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
