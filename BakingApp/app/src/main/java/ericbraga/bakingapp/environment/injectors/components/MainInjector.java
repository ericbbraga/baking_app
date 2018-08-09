package ericbraga.bakingapp.environment.injectors.components;

import javax.inject.Singleton;

import dagger.Component;
import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.environment.injectors.modules.AsyncReadRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.AsyncWriteRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.ChangeRecipeFavorteInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.FavoriteRecipeInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.MainModule;
import ericbraga.bakingapp.environment.injectors.modules.MainRouterModule;
import ericbraga.bakingapp.environment.injectors.modules.NetworkModule;
import ericbraga.bakingapp.environment.injectors.modules.NotifyModule;
import ericbraga.bakingapp.environment.injectors.modules.RecipeInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.UpdateStatusModule;
import ericbraga.bakingapp.environment.injectors.modules.WebUrlModule;

@Component(
        modules = {
                AsyncReadRepositoryModule.class,
                AsyncWriteRepositoryModule.class,
                ChangeRecipeFavorteInteractorModule.class,
                FavoriteRecipeInteractorModule.class,
                MainModule.class,
                MainRouterModule.class,
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