package ericbraga.bakingapp.environment.injectors.modules.common;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.environment.util.NetworkManager;

@Module
public class NetworkModule {
    @Provides @Singleton
    public NetworkManager provideNetworkManager(Context context) {
        return new NetworkManager(context);
    }
}
