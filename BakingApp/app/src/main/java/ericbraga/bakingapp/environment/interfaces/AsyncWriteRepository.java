package ericbraga.bakingapp.environment.interfaces;

import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public interface AsyncWriteRepository {
    void saveCollection(RecipeCollection collection, WriteRepositoryCallback callback);
    void update(Recipe recipe, WriteRepositoryCallback callback);

    interface WriteRepositoryCallback {
        void onSave();
        void onError(String message);
    }
    
}
