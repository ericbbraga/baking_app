package ericbraga.bakingapp.app;

import ericbraga.bakingapp.environment.application.App;
import ericbraga.bakingapp.environment.injectors.modules.AsyncReadRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.NetworkModule;
import ericbraga.bakingapp.injectors.RecipeInteractorModuleIdlingResourceModule;

public class MockApp extends App {

    public void setNetworkModule (NetworkModule networkModule) {
        mDaggerMainInjectorBuilder.networkModule(networkModule);
    }

    public void setAsyncReadRepositoryModule(AsyncReadRepositoryModule repositoryModule) {
        mDaggerMainInjectorBuilder.asyncReadRepositoryModule(repositoryModule);
    }

    public void setRecipeInteractorModule(
        RecipeInteractorModuleIdlingResourceModule recipeInteractorModule) {
        mDaggerMainInjectorBuilder.recipeInteractorModule(recipeInteractorModule);
    }
}
