package ericbraga.bakingapp.environment.common.repositories.web;

import ericbraga.bakingapp.environment.common.repositories.AbstractRecipeRepository;
import ericbraga.bakingapp.environment.common.interfaces.Connection;
import ericbraga.bakingapp.environment.common.interfaces.Parser;

public class WebRecipeRepository extends AbstractRecipeRepository {

    private final String mUrl;

    public WebRecipeRepository(String url) {
        mUrl = url;
    }

    @Override
    protected Connection getConnection() {
        return new WebConnection(mUrl);
    }

    @Override
    protected Parser getParser() {
        return new JsonWebParser();
    }
}
