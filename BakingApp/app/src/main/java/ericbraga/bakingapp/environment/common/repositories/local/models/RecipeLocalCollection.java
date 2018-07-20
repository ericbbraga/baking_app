package ericbraga.bakingapp.environment.common.repositories.local.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipeLocalCollection {

    private List<RecipeLocal> mRecipes;

    public RecipeLocalCollection(List<RecipeLocal> recipes) {
        mRecipes = new ArrayList<>(recipes);
    }

    public List<RecipeLocal> getRecipes() {
        return mRecipes;
    }

    public Iterator<RecipeLocal> iterator() {
        return mRecipes.iterator();
    }

    public int size() {
        return mRecipes.size();
    }
}
