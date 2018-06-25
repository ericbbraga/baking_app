package ericbraga.bakingapp.environment.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.environment.common.repositories.web.models.RecipeWeb;

public class RecipeWebCollection {

    private List<RecipeWeb> mRecipes;

    public RecipeWebCollection(List<RecipeWeb> recipes) {
        mRecipes = new ArrayList<>(recipes);
    }

    // TODO: Move this method to some aux one
    public boolean recipesValid() {
        boolean valid = true;

        for (RecipeWeb recipe : mRecipes) {
            if (recipe.getName() == null ||
                    recipe.getStepWebs() == null ||
                    recipe.getIngredientWeb() == null) {
                valid = false;
                break;
            }
        }

        return valid;
    }

    public Iterator<RecipeWeb> iterator() {
        return mRecipes.iterator();
    }

    public int size() {
        return mRecipes.size();
    }
}
