package ericbraga.bakingapp.environment.application;

import android.app.Application;
import android.content.Context;

import ericbraga.bakingapp.environment.activities.DescriptionRecipeActivity;
import ericbraga.bakingapp.environment.activities.MainActivity;
import ericbraga.bakingapp.environment.injectors.components.DaggerDescriptionInjector;
import ericbraga.bakingapp.environment.injectors.components.DaggerMainInjector;
import ericbraga.bakingapp.environment.injectors.modules.common.ContextModule;
import ericbraga.bakingapp.environment.injectors.modules.description.DescriptionModule;

public class App extends Application {
    private DaggerMainInjector.Builder mMainDaggerInjectorBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
        configureMainInjectorBuilder();
    }

    protected void configureMainInjectorBuilder() {
        Context context = getApplicationContext();

        mMainDaggerInjectorBuilder = DaggerMainInjector.builder()
                .contextModule(new ContextModule(context));
    }

    public void inject(MainActivity activity) {
        mMainDaggerInjectorBuilder.build().inject(activity);
    }

    public void inject(DescriptionRecipeActivity activity) {
        DaggerDescriptionInjector.create().inject(activity);
    }
}
