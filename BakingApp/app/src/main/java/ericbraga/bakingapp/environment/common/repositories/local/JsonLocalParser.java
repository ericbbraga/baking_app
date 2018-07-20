package ericbraga.bakingapp.environment.common.repositories.local;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ericbraga.bakingapp.environment.common.interfaces.Parser;
import ericbraga.bakingapp.environment.common.repositories.exception.ParserException;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocalCollection;

public class JsonLocalParser implements Parser<List<RecipeLocal>>{
    @Override
    public List<RecipeLocal> parse(String content) throws ParserException {

        List<RecipeLocal> recipes = new ArrayList<>();
        if (content == null) {
            throw new ParserException("Content could not be null");
        }

        if (!content.isEmpty()) {
            try{
                Gson gson = new GsonBuilder().create();
                Type listType = new TypeToken<List<RecipeLocal>>(){}.getType();
                recipes = gson.fromJson(content, listType);

            } catch(JsonSyntaxException e) {
                throw new ParserException(e.getMessage());
            }
        }

        return recipes;
    }

    @Override
    public String toJson(List<RecipeLocal> object) throws ParserException {
        try{
            Gson gson = new GsonBuilder().create();
            return gson.toJson(object);

        } catch(JsonSyntaxException e) {
            throw new ParserException(e.getMessage());
        }
    }
}
