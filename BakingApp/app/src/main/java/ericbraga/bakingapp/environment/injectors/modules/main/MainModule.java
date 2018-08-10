package ericbraga.bakingapp.environment.injectors.modules.main;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.interactor.interfaces.ChangeRecipeFavoriteInteractor;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.presenter.DisplayRecipeCollectionPresenter;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;

@Module
public class MainModule {

    @Provides @Singleton
    public DisplayRecipesContract.Presenter providePresenter(RecipeInteractor recipeInteractor,
            ChangeRecipeFavoriteInteractor changeRecipeFavoriteInteractor) {
        return new DisplayRecipeCollectionPresenter(recipeInteractor,
                changeRecipeFavoriteInteractor);
    }
}
