package ericbraga.bakingapp.environment.repositories.web.boundary;

import java.util.List;

import ericbraga.bakingapp.environment.repositories.web.RecipeWebCollection;
import ericbraga.bakingapp.environment.repositories.web.models.RecipeWeb;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;

public class RecipeCollectionWebMapper {

    private RecipeWebMapper mRecipeWebMapper;

    public RecipeCollectionWebMapper() {
        mRecipeWebMapper = new RecipeWebMapper();
    }

    public RecipeCollection fromWebRecipe(RecipeWebCollection recipeWebCollection) {
        RecipeCollection collection = new RecipeCollection();
        List<RecipeWeb> recipeWebs = recipeWebCollection.getRecipes();

        for (RecipeWeb recipeWeb : recipeWebs) {
            Recipe recipe = mRecipeWebMapper.fromWebRecipe(recipeWeb);
            collection.addRecipe(recipe);
        }

        return collection;
    }


}
