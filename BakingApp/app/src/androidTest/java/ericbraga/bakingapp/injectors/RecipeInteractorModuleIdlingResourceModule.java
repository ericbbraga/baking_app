package ericbraga.bakingapp.injectors;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;

import ericbraga.bakingapp.environment.injectors.modules.common.RecipeInteractorModule;
import ericbraga.bakingapp.interactor.implementation.LoadRecipes;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.model.RecipeCollection;

public class RecipeInteractorModuleIdlingResourceModule extends RecipeInteractorModule {

    @Override
    public RecipeInteractor provideRecipeInteractor(AsyncReadRepository repository) {
        return new LoadRecipeIdling(repository);
    }
}

class LoadRecipeIdling extends LoadRecipes {
    private final CountingIdlingResource mIdlingResource;
    private final IdlingRegistry mRegistry;
    
    LoadRecipeIdling(AsyncReadRepository read) {
        super(read);
        mIdlingResource = new CountingIdlingResource("Sync Recipes");
        mRegistry = IdlingRegistry.getInstance();
        mRegistry.register(mIdlingResource);
    }

    @Override
    public void execute(Callback callback) {
        super.execute(callback);
        mIdlingResource.increment();
    }

    @Override
    public void errorMessage(String message) {
        super.errorMessage(message);
        unregisterIdlingResource();
    }

    @Override
    public void listRecipesContent(RecipeCollection recipes) {
        super.listRecipesContent(recipes);
        unregisterIdlingResource();
    }

    private void unregisterIdlingResource() {
        mIdlingResource.decrement();
        mRegistry.unregister(mIdlingResource);
    }
}