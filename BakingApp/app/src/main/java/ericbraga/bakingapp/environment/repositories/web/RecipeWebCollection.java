package ericbraga.bakingapp.environment.repositories.web;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.environment.repositories.web.models.RecipeWeb;

public class RecipeWebCollection {

    private List<RecipeWeb> mRecipes;

    public RecipeWebCollection(List<RecipeWeb> recipes) {
        mRecipes = new ArrayList<>(recipes);
    }

    public List<RecipeWeb> getRecipes() {
        return mRecipes;
    }

    public int size() {
        return mRecipes.size();
    }
}
