package ericbraga.bakingapp.environment.common.repositories;

import android.content.Context;

import ericbraga.bakingapp.environment.android.util.NetworkManager;
import ericbraga.bakingapp.environment.common.repositories.proxy.ProxyRecipeReadRepository;
import ericbraga.bakingapp.environment.common.repositories.web.WebRecipeReadRepository;
import ericbraga.bakingapp.interactor.interfaces.ReadRepository;

public class RepositoryFactory {

    public static ReadRepository getRepository(boolean useCache, Context context, String url) {
        if (useCache) {
            NetworkManager networkManager = new NetworkManager(context);
            return new ProxyRecipeReadRepository(context,networkManager, url);
        } else {
            return new WebRecipeReadRepository(url);
        }
    }

}
