package ericbraga.bakingapp.environment.repositories.local.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.repositories.local.models.entity.IngredientEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.RecipeEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.RecipeIngredientsSteps;
import ericbraga.bakingapp.environment.repositories.local.models.entity.StepEntity;

public class LocalDatabase {

    private BakingDatabase mDatabase;
    private static LocalDatabase sInstance;

    public static synchronized LocalDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LocalDatabase(context);
        }

        return sInstance;
    }

    private LocalDatabase(Context context) {
        mDatabase = Room.databaseBuilder(context, BakingDatabase.class, "baking").build();
    }

    public long save(RecipeLocal recipe) {
        List<IngredientEntity> ingredients = recipe.getIngredients();
        List<StepEntity> steps = recipe.getSteps();
        long recipeId = -1;

        if (!recipeExists(recipe)) {
            recipeId = mDatabase.recipeDao().insert(recipe.getRecipeEntity());

            addIngredientsForRecipe(ingredients, recipeId);
            addStepsForRecipe(steps, recipeId);
        }

        return recipeId;
    }

    private boolean recipeExists(RecipeLocal recipe) {
        return findRecipeById(recipe.getRecipeEntity().getId()) != null;
    }

    private RecipeLocal findRecipeById(long id) {
        RecipeIngredientsSteps recipeIngredientsSteps = mDatabase.recipeDao().findRecipeById(id);
        return toRecipeLocal(recipeIngredientsSteps);
    }

    private void addIngredientsForRecipe(List<IngredientEntity> ingredients, long recipeId) {
        for (IngredientEntity ingredient : ingredients) {
            ingredient.setRecipeId(recipeId);
            mDatabase.ingredientDao().insert(ingredient);
        }
    }

    private void addStepsForRecipe(List<StepEntity> steps, long recipeId) {
        for (StepEntity step : steps) {
            step.setRecipeId(recipeId);
            mDatabase.stepDao().insert(step);
        }
    }

    public void update(RecipeLocal recipe) {
        RecipeEntity recipeEntity = recipe.getRecipeEntity();
        List<IngredientEntity> ingredientsEntity = recipe.getIngredients();
        List<StepEntity> stepsEntity = recipe.getSteps();

        mDatabase.recipeDao().update(recipeEntity);
        mDatabase.ingredientDao().update(ingredientsEntity);
        mDatabase.stepDao().update(stepsEntity);
    }

    public List<RecipeLocal> recipes() {
        List<RecipeIngredientsSteps> recipeIngredientsSteps = mDatabase.recipeDao().loadRecipes();
        return createRecipes(recipeIngredientsSteps);
    }

    public RecipeLocal loadRecipeStarred() {
        RecipeIngredientsSteps recipeIngredientsSteps =
                mDatabase.recipeDao().loadRecipeStarred();
        return toRecipeLocal(recipeIngredientsSteps);
    }

    private List<RecipeLocal> createRecipes(List<RecipeIngredientsSteps> recipeIngredientsSteps) {
        List<RecipeLocal> recipes = new ArrayList<>();

        for (RecipeIngredientsSteps recipeIngredientsStep : recipeIngredientsSteps) {
            RecipeLocal recipe = toRecipeLocal(recipeIngredientsStep);
            recipes.add(recipe);
        }

        return recipes;
    }

    private RecipeLocal toRecipeLocal(RecipeIngredientsSteps recipeIngredientsStep) {

        if (recipeIngredientsStep == null) {
            return null;
        }

        return new RecipeLocal(
                        recipeIngredientsStep.recipeEntry.getId(),
                        recipeIngredientsStep.recipeEntry.getName(),
                        recipeIngredientsStep.recipeEntry.isStarred(),
                        recipeIngredientsStep.ingredients,
                        recipeIngredientsStep.steps
                );
    }
}
