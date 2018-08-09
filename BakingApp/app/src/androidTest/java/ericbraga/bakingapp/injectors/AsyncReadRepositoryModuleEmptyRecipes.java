package ericbraga.bakingapp.injectors;

import android.content.Context;

import ericbraga.bakingapp.environment.injectors.modules.AsyncReadRepositoryModule;
import ericbraga.bakingapp.environment.repositories.local.repositories.LocalRecipeRepository;
import ericbraga.bakingapp.environment.util.NetworkManager;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
import ericbraga.bakingapp.model.RecipeCollection;

public class AsyncReadRepositoryModuleEmptyRecipes extends AsyncReadRepositoryModule {
    private final Context mContext;

    public AsyncReadRepositoryModuleEmptyRecipes(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public AsyncReadRepository provideAsyncReadRepository(String webUrl,
                                                          NetworkManager networkManager) {
        return new AsyncReadRepositoryEmpty(mContext);
    }
}

class AsyncReadRepositoryEmpty extends LocalRecipeRepository {

    AsyncReadRepositoryEmpty(Context context) {
        super(context);
    }

    @Override
    public void listRecipes(RepositoryCallback callback) {
        callback.listRecipesContent(RecipeCollection.emptyCollection());
    }
}
