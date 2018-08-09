package ericbraga.bakingapp.environment.repositories;

import android.content.Context;

import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.environment.repositories.local.repositories.LocalRecipeRepository;
import ericbraga.bakingapp.environment.repositories.proxy.ProxyRecipeReadRepository;
import ericbraga.bakingapp.environment.util.NetworkManager;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;

public class RepositoryFactory {

    public static AsyncReadRepository getRepository(Context context, String url,
                                                    NetworkManager networkManager) {
        return new ProxyRecipeReadRepository(context,networkManager, url);
    }

    public static AsyncWriteRepository getWriteRepository(Context context) {
        return new LocalRecipeRepository(context);
    }

}
