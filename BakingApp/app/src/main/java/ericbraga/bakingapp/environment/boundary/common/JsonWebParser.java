package ericbraga.bakingapp.environment.boundary.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ericbraga.bakingapp.environment.boundary.common.interfaces.Parser;
import ericbraga.bakingapp.model.Recipe;

class JsonWebParser implements Parser {
    @Override
    public List<Recipe> parse(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Content could not be null");
        }

        if (content.isEmpty()) {
            return Collections.emptyList();
        }

        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Recipe>>(){}.getType();
        return gson.fromJson(content, listType);
    }
}
