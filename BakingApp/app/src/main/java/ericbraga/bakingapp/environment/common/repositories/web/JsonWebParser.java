package ericbraga.bakingapp.environment.common.repositories.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import ericbraga.bakingapp.environment.common.WebRecipeCollection;
import ericbraga.bakingapp.environment.common.interfaces.Parser;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;
import ericbraga.bakingapp.environment.common.repositories.web.models.RecipeWeb;

class JsonWebParser implements Parser {
    @Override
    public WebRecipeCollection parse(String content) throws ParserException {
        if (content == null) {
            throw new ParserException("Content could not be null");
        }

        if (content.isEmpty()) {
            List<RecipeWeb> recipes = Collections.emptyList();
            return new WebRecipeCollection(recipes);
        }

        try {
            Gson gson = new GsonBuilder().create();
            Type listType = new TypeToken<List<RecipeWeb>>(){}.getType();
            List<RecipeWeb> recipes = gson.fromJson(content, listType);
            return new WebRecipeCollection(recipes);

        } catch(JsonSyntaxException e) {
            throw new ParserException(e.getMessage());
        }
    }
}
