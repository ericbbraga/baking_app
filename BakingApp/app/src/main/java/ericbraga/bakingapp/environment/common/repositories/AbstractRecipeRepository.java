package ericbraga.bakingapp.environment.common.repositories;

import ericbraga.bakingapp.environment.common.WebRecipeCollection;
import ericbraga.bakingapp.environment.common.interfaces.Connection;
import ericbraga.bakingapp.environment.common.interfaces.Parser;
import ericbraga.bakingapp.environment.common.interfaces.Repository;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;

public abstract class AbstractRecipeRepository implements Repository, Connection.Callback {

    private RepositoryCallback mCallback;

    @Override
    public void listRecipes(RepositoryCallback callback) {
        mCallback = callback;

        Connection connection = getConnection();
        connection.connect(this);
    }

    protected abstract Connection getConnection();

    @Override
    public void onSuccess(String result) {
        if (mCallback != null) {
            WebRecipeCollection collection = null;
            try {
                collection = getParser().parse(result);
                mCallback.listRecipesContent(collection);

            } catch (ParserException e) {
                mCallback.errorMessage(e.getMessage());
            }
        }
    }

    protected abstract Parser getParser();

    @Override
    public void onError(String errorMessage) {
        if (mCallback != null) {
            mCallback.errorMessage(errorMessage);
        }
    }
}
