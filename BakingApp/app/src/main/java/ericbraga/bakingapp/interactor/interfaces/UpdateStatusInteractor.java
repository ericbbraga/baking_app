package ericbraga.bakingapp.interactor.interfaces;

import ericbraga.bakingapp.model.Recipe;

public interface UpdateStatusInteractor {
    boolean execute(Recipe recipe);
    void execute(Recipe recipe, UpdateStatusInteractor.Callback callback);

    interface Callback {
        void onUpdate();
        void onError(String message);
    }

}
