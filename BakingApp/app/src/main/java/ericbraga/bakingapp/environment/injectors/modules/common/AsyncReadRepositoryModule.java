package ericbraga.bakingapp.environment.injectors.modules.common;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.environment.repositories.RepositoryFactory;
import ericbraga.bakingapp.environment.util.NetworkManager;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;

@Module
public class AsyncReadRepositoryModule {

    @Provides
    public AsyncReadRepository provideAsyncReadRepository(Context context, String webUrl,
                                                          NetworkManager networkManager) {
        return RepositoryFactory.getRepository(context, webUrl, networkManager);
    }
}
