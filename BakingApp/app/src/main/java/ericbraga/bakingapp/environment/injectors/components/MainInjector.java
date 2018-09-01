package ericbraga.bakingapp.environment.injectors.components;

import javax.inject.Singleton;

import dagger.Component;
import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.environment.injectors.modules.common.AsyncReadRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.common.AsyncWriteRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.common.ChangeRecipeFavorteInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.common.ContextModule;
import ericbraga.bakingapp.environment.injectors.modules.common.FavoriteRecipeInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.main.MainModule;
import ericbraga.bakingapp.environment.injectors.modules.common.NetworkModule;
import ericbraga.bakingapp.environment.injectors.modules.common.NotifyModule;
import ericbraga.bakingapp.environment.injectors.modules.common.RecipeInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.common.UpdateStatusModule;
import ericbraga.bakingapp.environment.injectors.modules.common.WebUrlModule;

@Component(
        modules = {
                AsyncReadRepositoryModule.class,
                AsyncWriteRepositoryModule.class,
                ChangeRecipeFavorteInteractorModule.class,
                ContextModule.class,
                FavoriteRecipeInteractorModule.class,
                MainModule.class,
                NetworkModule.class,
                NotifyModule.class,
                WebUrlModule.class,
                RecipeInteractorModule.class,
                UpdateStatusModule.class,
                WebUrlModule.class
        }
)
@Singleton
public interface MainInjector {
    void inject(MainActivity activity);
}