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

    public Recipe getElement(int position) {
        if (position <= 0 && position > mRecipes.size()) {
            throw new IllegalArgumentException("Position is out of range");
        }

        return mRecipes.get(position);
    }


    public int size() {
        return mRecipes.size();
    }
}
