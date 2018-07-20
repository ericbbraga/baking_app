package ericbraga.bakingapp.environment.common.repositories.local;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import ericbraga.bakingapp.boundary.RecipeCollectionLocalMapper;
import ericbraga.bakingapp.environment.common.interfaces.ReadWriteRepository;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;
import ericbraga.bakingapp.environment.common.repositories.local.database.LocalRepository;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocalCollection;
import ericbraga.bakingapp.model.RecipeCollection;

public class LocalRecipeReadRepository implements ReadWriteRepository {
    private final LocalRepository mRepository;

    public LocalRecipeReadRepository(Context context) {
        mRepository = new LocalRepository(context);
    }

    @Override
    public void listRecipes(RepositoryCallback callback) {
        if (callback != null) {
            try {
                String content = mRepository.readContent();

                RecipeCollectionLocalMapper mapper = new RecipeCollectionLocalMapper();
                List<RecipeLocal> recipes = new JsonLocalParser().parse(content);
                RecipeLocalCollection localCollection = new RecipeLocalCollection(recipes);
                RecipeCollection collection = mapper.fromLocalRecipe(localCollection);

                callback.listRecipesContent(collection);

            } catch (IOException | ParserException e) {
                callback.errorMessage(e.getMessage());
            }
        }
    }

    @Override
    public void saveCollection(RecipeCollection collection, WriteRepositoryCallback callback) {
        RecipeCollectionLocalMapper mapper = new RecipeCollectionLocalMapper();
        RecipeLocalCollection localCollection = mapper.toLocalRecipe(collection);

        try {
            String content = new JsonLocalParser().toJson(localCollection.getRecipes());
            mRepository.saveContent(content);
            callback.onSaveCollection();
        } catch (ParserException | IOException e) {
            callback.onError(e.getMessage());
        }
    }
}
