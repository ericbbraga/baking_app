package ericbraga.bakingapp.environment.injectors.modules;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.interactor.implementation.GetFavoriteRecipe;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.interactor.interfaces.GetFavoriteRecipeInteractor;

@Module
public class FavoriteRecipeInteractorModule {

    @Provides
    public GetFavoriteRecipeInteractor provideGetFavoriteRecipe(AsyncReadRepository repository) {
        return new GetFavoriteRecipe(repository);
    }

}
