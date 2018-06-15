package ericbraga.bakingapp.interactor.implementation;

import java.util.List;

import ericbraga.bakingapp.bondary.Connection;
import ericbraga.bakingapp.interactor.RecipeInteractor;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.utils.Parser;

public class LoadRecipes implements RecipeInteractor, Connection.Callback {
    private final String mUrl;
    private final Connection mConnection;
    private final Parser mParser;
    private final RecipeInteractor.Callback mCallback;

    public LoadRecipes(String url,
                       Connection connection,
                       Parser parser,
                       Callback callback) {
        mConnection = connection;
        mUrl = url;
        mParser = parser;
        mCallback = callback;
    }

    @Override
    public void load() {
        mConnection.connect(mUrl, this);
    }

    @Override
    public void onSuccess(String result) {
        if (mCallback != null) {
            List<Recipe> recipes = parseResult(result);
            mCallback.onResultReceive(recipes);
        }
    }

    @Override
    public void onError(String errorMessage) {
        // TODO: Implements Error
        if (mCallback != null) {

        }
    }

    private List<Recipe> parseResult(String result) {
        return mParser.parse(result);
    }
}
