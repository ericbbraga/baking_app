package ericbraga.bakingapp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipeCollection {

    private List<Recipe> mRecipes;

    public RecipeCollection() {
        mRecipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe could not be null");
        }

        mRecipes.add(recipe);
    }

    public Iterator<Recipe> iterator() {
        return mRecipes.iterator();
    }
}
