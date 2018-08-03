package ericbraga.bakingapp.environment.repositories.proxy;

import android.content.Context;

import ericbraga.bakingapp.environment.interfaces.AsyncReadWriteRepository;
import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.environment.interfaces.ExternalRepository;
import ericbraga.bakingapp.environment.repositories.local.repositories.LocalRecipeRepository;
import ericbraga.bakingapp.environment.repositories.web.WebRecipeReadRepository;
import ericbraga.bakingapp.environment.util.NetworkManager;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.model.RecipeCollection;

public class ProxyRecipeReadRepository implements AsyncReadRepository {
    private final NetworkManager mNetworkManager;
    private AsyncReadWriteRepository mLocalReadRepository;
    private ExternalRepository mWebReadRepository;
    private RepositoryCallback mOutsideCallback;
    private boolean loadRecipes = false;

    public ProxyRecipeReadRepository(Context context, NetworkManager networkManager, String url) {
        mNetworkManager = networkManager;
        mLocalReadRepository = new LocalRecipeRepository(context);
        mWebReadRepository = new WebRecipeReadRepository(url);
    }

    @Override
    public void listRecipes(RepositoryCallback callback) {
        loadRecipes = false;
        mOutsideCallback = callback;
        mLocalReadRepository.listRecipes(new LocalCallback());
    }

    @Override
    public void getStarredRecipe(RepositoryCallbackStarredRecipe callback) {
        mLocalReadRepository.getStarredRecipe(callback);
    }


    private class LocalCallback implements RepositoryCallback {
        @Override
        public void listRecipesContent(RecipeCollection recipes) {
            if (recipes.size() > 0) {
                loadRecipes = true;
                mOutsideCallback.listRecipesContent(recipes);
            }

            if (mNetworkManager.hasConnection()) {
                mWebReadRepository.listRecipes(new WebCallback());
            }
        }
        @Override
        public void errorMessage(String message) {
            mOutsideCallback.errorMessage(message);
        }
    }

    private class WebCallback implements ExternalRepository.RepositoryCallback {
        @Override
        public void listRecipesContent(RecipeCollection recipes) {
            if (!loadRecipes) {
                loadRecipes = true;
                mOutsideCallback.listRecipesContent(recipes);
            }

            mLocalReadRepository.saveCollection(recipes, new LocalWriteRepositoryCallback());
        }

        @Override
        public void errorMessage(String message) {
            mOutsideCallback.errorMessage(message);
        }
    }

    private class LocalWriteRepositoryCallback implements
            AsyncWriteRepository.WriteRepositoryCallback {

        @Override
        public void onSave() {
            // TODO: Log Here
        }

        @Override
        public void onError(String message) {
            // TODO: Log Here
        }
    }
}
