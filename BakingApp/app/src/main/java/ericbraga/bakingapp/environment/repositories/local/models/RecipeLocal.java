package ericbraga.bakingapp.environment.repositories.local.models;

import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.models.entity.IngredientEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.RecipeEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.StepEntity;

public class RecipeLocal {
    private RecipeEntity mRecipeEntity;
    private List<IngredientEntity> mIngredients;
    private List<StepEntity> mSteps;

    public RecipeLocal(long id, String name, boolean starred,
                       List<IngredientEntity> ingredients,
                       List<StepEntity> steps) {

        mRecipeEntity = new RecipeEntity(id, name, starred);
        mIngredients = ingredients;
        mSteps = steps;
    }

    public RecipeEntity getRecipeEntity() {
        return mRecipeEntity;
    }

    public List<IngredientEntity> getIngredients() {
        return mIngredients;
    }

    public List<StepEntity> getSteps() {
        return mSteps;
    }
}
