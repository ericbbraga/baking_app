package ericbraga.bakingapp.environment.repositories.local.boundary;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.environment.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.repositories.local.models.entity.IngredientEntity;
import ericbraga.bakingapp.environment.repositories.local.models.entity.StepEntity;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;

public class RecipeLocalMapper {
    public Recipe fromLocalRecipe(RecipeLocal recipeLocal) {
        long id = recipeLocal.getRecipeEntity().getId();
        String name = recipeLocal.getRecipeEntity().getName();
        boolean starred = recipeLocal.getRecipeEntity().isStarred();

        List<Ingredient> ingredients = getIngredientsFrom(recipeLocal);
        List<Step> steps = getStepsFrom(recipeLocal);

        return new Recipe(id, name, ingredients, steps, starred);
    }

    private List<Ingredient> getIngredientsFrom(RecipeLocal recipeLocal) {
        List<IngredientEntity> ingredientsLocal = recipeLocal.getIngredients();
        List<Ingredient> ingredients = new ArrayList<>();

        for (IngredientEntity ingredientLocal : ingredientsLocal) {
            Ingredient ingredient = new Ingredient(
                    ingredientLocal.getName(),
                    ingredientLocal.getQuantity(),
                    ingredientLocal.getMeasure()
            );

            ingredients.add(ingredient);
        }
        return ingredients;
    }

    private List<Step> getStepsFrom(RecipeLocal recipeLocal) {
        List<StepEntity> stepsLocal = recipeLocal.getSteps();
        List<Step> steps = new ArrayList<>();

        for (StepEntity stepLocal : stepsLocal) {

            Step step = new Step(
                    stepLocal.getOrder(),
                    stepLocal.getShortDescription(),
                    stepLocal.getDescription(),
                    stepLocal.getVideoUrl(),
                    stepLocal.getThumbnail()
            );

            steps.add(step);
        }
        return steps;
    }

    public RecipeLocal toLocalRecipe(Recipe recipe) {
        long id = recipe.getId();
        String name = recipe.getName();
        List<IngredientEntity> ingredients = getIngredientsFrom(recipe);
        List<StepEntity> steps = getStepsFrom(recipe);
        boolean starred = recipe.isStarred();

        return new RecipeLocal(id, name, starred, ingredients, steps);
    }


    private List<IngredientEntity> getIngredientsFrom(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        List<IngredientEntity> ingredientsLocal = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            IngredientEntity ingredientLocal = new IngredientEntity(
                    ingredient.getName(),
                    ingredient.getMeasure(),
                    ingredient.getQuantity(),
                    recipe.getId()
            );

            ingredientsLocal.add(ingredientLocal);
        }

        return ingredientsLocal;
    }

    private List<StepEntity> getStepsFrom(Recipe recipe) {
        List<Step> steps = recipe.getSteps();
        List<StepEntity> stepsLocal = new ArrayList<>();

        for (Step step : steps) {
            StepEntity stepLocal = new StepEntity(
                    step.getDescription(),
                    step.getShortDescription(),
                    step.getOrder(),
                    step.getVideoUrl(),
                    step.getThumbnailUrl(),
                    recipe.getId()
            );


            stepsLocal.add(stepLocal);
        }

        return stepsLocal;
    }

}
