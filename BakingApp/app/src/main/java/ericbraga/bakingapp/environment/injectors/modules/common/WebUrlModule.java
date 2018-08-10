package ericbraga.bakingapp.environment.injectors.modules.common;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WebUrlModule {
    @Provides @Singleton
    public String provideDefaultUrl() {
        return "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    }

}
