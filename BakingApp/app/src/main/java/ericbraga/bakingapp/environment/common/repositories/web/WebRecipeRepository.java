package ericbraga.bakingapp.environment.common.repositories.web;

import ericbraga.bakingapp.boundary.RecipeCollectionWebMapper;
import ericbraga.bakingapp.environment.common.RecipeWebCollection;
import ericbraga.bakingapp.environment.common.interfaces.Connection;
import ericbraga.bakingapp.environment.common.interfaces.Repository;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;
import ericbraga.bakingapp.model.RecipeCollection;

public class WebRecipeRepository implements Repository, Connection.Callback {

    private final String mUrl;
    private RepositoryCallback mCallback;
    private RecipeCollectionWebMapper mMapper;

    public WebRecipeRepository(String url, RecipeCollectionWebMapper mapper) {
        mUrl = url;
        mMapper = mapper;
    }

    @Override
    public void listRecipes(RepositoryCallback callback) {
        mCallback = callback;
        initProcess();
    }

    private void initProcess() {
        Connection connection = new WebConnection(mUrl);
        connection.connect(this);
    }

    @Override
    public void onSuccess(String result) {
        if (mCallback != null) {
            try {
                RecipeWebCollection collectionWeb = new JsonWebParser().parse(result);
                RecipeCollection collection = mMapper.fromWebRecipe(collectionWeb);
                mCallback.listRecipesContent(collection);

            } catch (ParserException e) {
                mCallback.errorMessage(e.getMessage());
            }
        }
    }

    @Override
    public void onError(String errorMessage) {
        mCallback.errorMessage(errorMessage);
    }

}
