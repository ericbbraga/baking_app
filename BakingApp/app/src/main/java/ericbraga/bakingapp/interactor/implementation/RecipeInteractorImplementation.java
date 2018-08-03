package ericbraga.bakingapp.interactor.implementation;

import java.io.IOException;

import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.environment.interfaces.WriteRepository;
import ericbraga.bakingapp.environment.repositories.exception.ParserException;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public class RecipeInteractorImplementation implements RecipeInteractor,
        AsyncReadRepository.RepositoryCallback, AsyncWriteRepository.WriteRepositoryCallback {

    private final WriteRepository mWriteRepository;
    private final AsyncReadRepository mReadRepository;
    private RecipeInteractor.Callback mCallback;

    public RecipeInteractorImplementation(AsyncReadRepository read, WriteRepository write) {
        if (read == null) {
            throw new IllegalArgumentException("AsyncReadRepository could not be null");
        }

        if (write == null) {
            throw new IllegalArgumentException("AsyncWriteRepository could not be null");
        }

        mReadRepository = read;
        mWriteRepository = write;
    }

    @Override
    public void load(RecipeInteractor.Callback callback) {
        mCallback = callback;
        mReadRepository.listRecipes(this);
    }

    @Override
    public void changeRecipeStarred(final Recipe recipeStarred) {
        mReadRepository.getStarredRecipe(new AsyncReadRepository.RepositoryCallbackStarredRecipe() {
            @Override
            public void starredRecipe(Recipe recipe) {
                try {
                    if (recipe != null) {
                        recipe.setStarred(false);
                        mWriteRepository.update(recipe);
                    }
                    mWriteRepository.update(recipeStarred);

                } catch (IOException | ParserException e) {
                    mCallback.onError(e.getMessage());
                }
            }

            @Override
            public void errorMessage(String message) {

            }
        });

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

    @Override
    public void onSave() {
        if (mCallback != null) {
            mCallback.onUpdateRecipe();
        }
    }

    @Override
    public void onError(String message) {

    }
}
