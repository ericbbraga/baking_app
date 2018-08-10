package ericbraga.bakingapp.environment.application;

import android.app.Application;
import android.content.Context;

import ericbraga.bakingapp.environment.activities.DescriptionRecipeActivity;
import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.environment.injectors.components.DaggerDescriptionInjector;
import ericbraga.bakingapp.environment.injectors.components.DaggerMainInjector;
import ericbraga.bakingapp.environment.injectors.modules.common.AsyncReadRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.common.AsyncWriteRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.common.ChangeRecipeFavorteInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.common.FavoriteRecipeInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.description.DescriptionModule;
import ericbraga.bakingapp.environment.injectors.modules.main.MainModule;
import ericbraga.bakingapp.environment.injectors.modules.common.NetworkModule;
import ericbraga.bakingapp.environment.injectors.modules.common.NotifyModule;
import ericbraga.bakingapp.environment.injectors.modules.common.RecipeInteractorModule;
import ericbraga.bakingapp.environment.injectors.modules.common.UpdateStatusModule;
import ericbraga.bakingapp.environment.injectors.modules.common.WebUrlModule;

public class App extends Application {
    protected DaggerMainInjector.Builder mMainDaggerInjectorBuilder;
    protected DaggerDescriptionInjector.Builder mDescriptionDaggerInjectorBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        configureMainInjectorBuilder();
    }

    protected void configureMainInjectorBuilder() {
        Context context = getApplicationContext();

        mMainDaggerInjectorBuilder = DaggerMainInjector.builder()
            .asyncReadRepositoryModule(new AsyncReadRepositoryModule(context))
            .asyncWriteRepositoryModule(new AsyncWriteRepositoryModule(context))
            .changeRecipeFavorteInteractorModule(new ChangeRecipeFavorteInteractorModule())
            .favoriteRecipeInteractorModule(new FavoriteRecipeInteractorModule())
            .mainModule(new MainModule())
            .networkModule(new NetworkModule(context))
            .notifyModule(new NotifyModule(context))
            .recipeInteractorModule(new RecipeInteractorModule())
            .updateStatusModule(new UpdateStatusModule())
            .webUrlModule(new WebUrlModule());

        mDescriptionDaggerInjectorBuilder = DaggerDescriptionInjector.builder()
            .descriptionModule(new DescriptionModule());

    }

    public void inject(MainActivity activity) {
        mMainDaggerInjectorBuilder.build().inject(activity);
    }

    public void inject(DescriptionRecipeActivity activity) {
        mDescriptionDaggerInjectorBuilder.build().inject(activity);
    }
}
