package ericbraga.bakingapp.interactor.implementation;

import ericbraga.bakingapp.interactor.interfaces.ReadRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.model.RecipeCollection;

public class LoadRecipes implements RecipeInteractor, ReadRepository.RepositoryCallback {

    private final ReadRepository mReadRepository;
    private RecipeInteractor.Callback mCallback;

    public LoadRecipes(ReadRepository readRepository) {
        if (readRepository == null) {
            throw new IllegalArgumentException("ReadRepository could not be null");
        }

        mReadRepository = readRepository;
    }

    @Override
    public void load(RecipeInteractor.Callback callback) {
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
