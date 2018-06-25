package ericbraga.bakingapp.environment.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.boundary.RecipeCollection;
import ericbraga.bakingapp.environment.common.repositories.web.models.RecipeWeb;

public class WebRecipeCollection implements RecipeCollection {

    private List<RecipeWeb> mRecipes;

    public WebRecipeCollection(List<RecipeWeb> recipes) {
        mRecipes = new ArrayList<>(recipes);
    }

    public boolean recipesValid() {
        boolean valid = true;

        for (RecipeWeb recipe : mRecipes) {
            if (recipe.getName() == null ||
                    recipe.getStepWebs() == null ||
                    recipe.getIngredientWebs() == null) {
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
