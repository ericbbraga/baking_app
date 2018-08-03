package ericbraga.bakingapp.environment.repositories.local.boundary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.repositories.local.models.RecipeLocalCollection;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public class RecipeCollectionLocalMapper {

    private final RecipeLocalMapper mRecipeLocalMapper;

    public RecipeCollectionLocalMapper() {
        mRecipeLocalMapper = new RecipeLocalMapper();
    }

    public RecipeCollection fromLocalRecipe(RecipeLocalCollection recipeLocalCollection) {
        RecipeCollection collection = new RecipeCollection();
        Iterator<RecipeLocal> it = recipeLocalCollection.iterator();

        while (it.hasNext()) {
            RecipeLocal recipeLocal = it.next();
            Recipe recipe = mRecipeLocalMapper.fromLocalRecipe(recipeLocal);
            collection.addRecipe(recipe);
        }

        return collection;
    }

    public RecipeLocalCollection toLocalRecipe(RecipeCollection collection) {
        List<RecipeLocal> recipesLocal = new ArrayList<>();
        Iterator<Recipe> it = collection.iterator();

        while (it.hasNext()) {
            Recipe recipe = it.next();

            RecipeLocal recipeLocal = mRecipeLocalMapper.toLocalRecipe(recipe);
            recipesLocal.add(recipeLocal);
        }

        return new RecipeLocalCollection(recipesLocal);
    }
}
