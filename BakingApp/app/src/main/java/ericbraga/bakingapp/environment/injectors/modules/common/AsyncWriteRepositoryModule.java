package ericbraga.bakingapp.environment.injectors.modules.common;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.environment.repositories.RepositoryFactory;

@Module
public class AsyncWriteRepositoryModule {
    private Context mContext;

    public AsyncWriteRepositoryModule(Context context) {
        mContext = context;
    }

    @Provides
    public AsyncWriteRepository provideAsyncWriteRepository() {
        return RepositoryFactory.getWriteRepository(mContext);
    }
}
