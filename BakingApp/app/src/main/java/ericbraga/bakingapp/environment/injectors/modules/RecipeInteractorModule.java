package ericbraga.bakingapp.environment.injectors.modules;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.interactor.implementation.LoadRecipes;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;

@Module
public class RecipeInteractorModule {
    @Provides
    public RecipeInteractor provideRecipeInteractor(AsyncReadRepository repository) {
        return new LoadRecipes(repository);
    }
}
