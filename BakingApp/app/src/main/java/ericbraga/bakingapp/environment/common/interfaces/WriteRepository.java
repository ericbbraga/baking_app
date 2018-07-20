package ericbraga.bakingapp.environment.common.interfaces;

import ericbraga.bakingapp.model.RecipeCollection;

public interface WriteRepository {
    void saveCollection(RecipeCollection collection, WriteRepositoryCallback callback);

    interface WriteRepositoryCallback {
        void onSaveCollection();
        void onError(String message);
    }
    
}
