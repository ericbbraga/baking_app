package ericbraga.bakingapp.environment.injectors.modules.common;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.environment.repositories.RepositoryFactory;

@Module
public class AsyncWriteRepositoryModule {
    @Provides
    public AsyncWriteRepository provideAsyncWriteRepository(Context context) {
        return RepositoryFactory.getWriteRepository(context);
    }
}
