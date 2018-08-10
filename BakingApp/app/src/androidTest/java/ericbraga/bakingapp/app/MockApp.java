package ericbraga.bakingapp.app;

import ericbraga.bakingapp.environment.application.App;
import ericbraga.bakingapp.environment.injectors.modules.common.AsyncReadRepositoryModule;
import ericbraga.bakingapp.environment.injectors.modules.common.NetworkModule;
import ericbraga.bakingapp.injectors.RecipeInteractorModuleIdlingResourceModule;

public class MockApp extends App {

    public void setNetworkModule (NetworkModule networkModule) {
        mMainDaggerInjectorBuilder.networkModule(networkModule);
    }

    public void setAsyncReadRepositoryModule(AsyncReadRepositoryModule repositoryModule) {
        mMainDaggerInjectorBuilder.asyncReadRepositoryModule(repositoryModule);
    }

    public void setRecipeInteractorModule(
        RecipeInteractorModuleIdlingResourceModule recipeInteractorModule) {
        mMainDaggerInjectorBuilder.recipeInteractorModule(recipeInteractorModule);
    }
}
