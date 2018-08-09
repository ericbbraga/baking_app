package ericbraga.bakingapp.environment.application;

import android.app.Application;
import android.content.Context;

import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.environment.injectors.components.DaggerMainInjector;
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

public class App extends Application {
    protected DaggerMainInjector.Builder mDaggerMainInjectorBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        configureMainInjectorBuilder();
    }

    protected void configureMainInjectorBuilder() {
        Context context = getApplicationContext();

        mDaggerMainInjectorBuilder = DaggerMainInjector.builder()
                .asyncReadRepositoryModule(new AsyncReadRepositoryModule(context))
                .asyncWriteRepositoryModule(new AsyncWriteRepositoryModule(context))
                .changeRecipeFavorteInteractorModule(new ChangeRecipeFavorteInteractorModule())
                .favoriteRecipeInteractorModule(new FavoriteRecipeInteractorModule())
                .mainModule(new MainModule())
                .mainRouterModule(new MainRouterModule(context))
                .networkModule(new NetworkModule(context))
                .notifyModule(new NotifyModule(context))
                .recipeInteractorModule(new RecipeInteractorModule())
                .updateStatusModule(new UpdateStatusModule())
                .webUrlModule(new WebUrlModule());
    }

    public void inject(MainActivity activity) {
        mDaggerMainInjectorBuilder.build().inject(activity);
    }
}
