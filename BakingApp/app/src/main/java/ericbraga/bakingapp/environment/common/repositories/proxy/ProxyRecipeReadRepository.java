package ericbraga.bakingapp.environment.common.repositories.proxy;

import android.content.Context;

import ericbraga.bakingapp.environment.android.util.NetworkManager;
import ericbraga.bakingapp.environment.common.interfaces.ReadWriteRepository;
import ericbraga.bakingapp.environment.common.interfaces.WriteRepository;
import ericbraga.bakingapp.environment.common.repositories.local.LocalRecipeReadRepository;
import ericbraga.bakingapp.environment.common.repositories.web.WebRecipeReadRepository;
import ericbraga.bakingapp.interactor.interfaces.ReadRepository;
import ericbraga.bakingapp.model.RecipeCollection;

public class ProxyRecipeReadRepository implements ReadRepository {
    private final NetworkManager mNetworkManager;
    private ReadWriteRepository mLocalReadRepository;
    private ReadRepository mWebReadRepository;
    private RepositoryCallback mOutsideCallback;

    public ProxyRecipeReadRepository(Context context, NetworkManager networkManager, String url) {
        mNetworkManager = networkManager;
        mLocalReadRepository = new LocalRecipeReadRepository(context);
        mWebReadRepository = new WebRecipeReadRepository(url);
    }

    @Override
    public void listRecipes(RepositoryCallback callback) {
        mOutsideCallback = callback;
        mLocalReadRepository.listRecipes(new LocalCallback());
    }

    private class BaseCallback implements RepositoryCallback {
        @Override
        public void listRecipesContent(RecipeCollection recipes) {
            mOutsideCallback.listRecipesContent(recipes);
        }

        @Override
        public void errorMessage(String message) {
            mOutsideCallback.errorMessage(message);
        }
    }

    private class LocalCallback extends BaseCallback {

        @Override
        public void listRecipesContent(RecipeCollection recipes) {
            if (recipes.size() > 0) {
                super.listRecipesContent(recipes);
            }

            if (mNetworkManager.hasConnection()) {
                mWebReadRepository.listRecipes(new WebCallback());
            }
        }
    }

    private class WebCallback extends BaseCallback {
        @Override
        public void listRecipesContent(final RecipeCollection recipes) {
            mLocalReadRepository.saveCollection(recipes,
                    new WriteRepository.WriteRepositoryCallback() {
                        @Override
                        public void onSaveCollection() {
                            WebCallback.super.listRecipesContent(recipes);
                        }

                        @Override
                        public void onError(String message) {
                            mOutsideCallback.errorMessage(message);
                        }
                    });
        }
    }
}
