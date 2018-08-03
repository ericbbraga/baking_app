package ericbraga.bakingapp.environment.repositories.web;

import ericbraga.bakingapp.environment.interfaces.Connection;
import ericbraga.bakingapp.environment.interfaces.ExternalRepository;
import ericbraga.bakingapp.environment.repositories.exception.ParserException;
import ericbraga.bakingapp.environment.repositories.web.boundary.RecipeCollectionWebMapper;
import ericbraga.bakingapp.model.RecipeCollection;

public class WebRecipeReadRepository implements ExternalRepository, Connection.Callback {

    private final String mUrl;
    private RepositoryCallback mCallback;

    public WebRecipeReadRepository(String url) {
        mUrl = url;
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
                RecipeCollectionWebMapper mapper = new RecipeCollectionWebMapper();
                RecipeWebCollection collectionWeb = new JsonWebParser().parse(result);

                RecipeCollection collection = mapper.fromWebRecipe(collectionWeb);
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
