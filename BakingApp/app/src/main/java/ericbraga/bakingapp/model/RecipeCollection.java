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
        if (position < 0 || position > mRecipes.size()) {
            throw new IllegalArgumentException("Position is out of range");
        }

        return mRecipes.get(position);
    }

    public int size() {
        return mRecipes.size();
    }

    public void clear() {
        mRecipes.clear();
    }

    public Recipe getRecipeById(long recipeId) {
        for (Recipe recipe : mRecipes) {
            if (recipe.getId() == recipeId) {
                return recipe;
            }
        }

        return null;
    }

    public boolean hasRecipe(Recipe recipe) {
        return mRecipes.contains(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        for (int i = 0; i < mRecipes.size(); i++) {
            Recipe recipeSwap = mRecipes.get(i);

            if (recipe.equals(recipeSwap)) {
                mRecipes.set(i, recipe);
                break;
            }
        }
    }

    public int getPosition(long recipeId) {
        for (int i = 0; i < mRecipes.size(); i++) {
            Recipe recipe = mRecipes.get(i);

            if (recipe.getId() == recipeId) {
                return i;
            }
        }

        return -1;
    }
}
