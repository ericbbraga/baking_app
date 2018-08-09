package ericbraga.bakingapp.environment.injectors.modules;

import dagger.Module;
import dagger.Provides;
import ericbraga.bakingapp.interactor.implementation.ChangeRecipeFavorite;
import ericbraga.bakingapp.interactor.interfaces.ChangeRecipeFavoriteInteractor;
import ericbraga.bakingapp.interactor.interfaces.GetFavoriteRecipeInteractor;
import ericbraga.bakingapp.interactor.interfaces.UpdateStatusInteractor;

@Module
public class ChangeRecipeFavorteInteractorModule {

    @Provides
    public ChangeRecipeFavoriteInteractor provideRecipeFavorteInteractor(
            UpdateStatusInteractor updateStatusInteractor,
            GetFavoriteRecipeInteractor favoriteInteractor) {
        return new ChangeRecipeFavorite(updateStatusInteractor, favoriteInteractor);
    }

}
