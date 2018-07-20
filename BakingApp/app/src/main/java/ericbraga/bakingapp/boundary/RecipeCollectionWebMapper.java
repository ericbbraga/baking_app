package ericbraga.bakingapp.boundary;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.environment.common.repositories.web.RecipeWebCollection;
import ericbraga.bakingapp.environment.common.repositories.local.models.IngredientLocal;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.common.repositories.local.models.StepLocal;
import ericbraga.bakingapp.environment.common.repositories.web.models.IngredientWeb;
import ericbraga.bakingapp.environment.common.repositories.web.models.RecipeWeb;
import ericbraga.bakingapp.environment.common.repositories.web.models.StepWeb;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.model.Step;

public class RecipeCollectionWebMapper {

    public RecipeCollection fromWebRecipe(RecipeWebCollection recipeWebCollection) {
        RecipeCollection collection = new RecipeCollection();
        Iterator<RecipeWeb> it = recipeWebCollection.iterator();

        while (it.hasNext()) {
            RecipeWeb recipeWeb = it.next();

            String name = recipeWeb.getName();
            List<Ingredient> ingredients = getIngredientsFrom(recipeWeb);
            List<Step> steps = getStepsFrom(recipeWeb);

            Recipe recipe = new Recipe(name, ingredients, steps);
            collection.addRecipe(recipe);
        }

        return collection;
    }

    @NonNull
    private List<Ingredient> getIngredientsFrom(RecipeWeb recipeWeb) {
        Iterator<IngredientWeb> iteratorIngredient = recipeWeb.getIngredientWeb();
        List<Ingredient> ingredients = new ArrayList<>();

        while (iteratorIngredient.hasNext()) {
            IngredientWeb ingredientWeb = iteratorIngredient.next();

            Ingredient ingredient = new Ingredient(
                    ingredientWeb.getName(),
                    ingredientWeb.getQuantity(),
                    ingredientWeb.getMeasure()
            );

            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @NonNull
    private List<Step> getStepsFrom(RecipeWeb recipeWeb) {
        Iterator<StepWeb> iteratorSteps = recipeWeb.getStepWebs();
        List<Step> steps = new ArrayList<>();

        while (iteratorSteps.hasNext()) {
            StepWeb stepWeb = iteratorSteps.next();

            Step step = new Step(
                    stepWeb.id(),
                    stepWeb.getShortDescription(),
                    stepWeb.getDescription(),
                    stepWeb.getVideoUrl(),
                    stepWeb.getThumbnailUrl()
            );

            steps.add(step);
        }
        return steps;
    }

    public RecipeWebCollection toWebRecipe(RecipeCollection collection) {
        List<RecipeWeb> recipes = new ArrayList<>();
        Iterator<Recipe> it = collection.iterator();

        while (it.hasNext()) {
            Recipe recipe = it.next();

            String name = recipe.getName();
            List<IngredientWeb> ingredients = getIngredientsFrom(recipe);
            List<StepWeb> steps = getStepsFrom(recipe);

            RecipeWeb recipeWeb = new RecipeWeb(name, ingredients, steps);
            collection.addRecipe(recipe);
        }

        return new RecipeWebCollection(recipes);
    }

    private List<IngredientWeb> getIngredientsFrom(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        List<IngredientWeb> ingredientWebs = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            IngredientWeb ingredientWeb = new IngredientWeb(
                    ingredient.getName(),
                    ingredient.getQuantity(),
                    ingredient.getMeasure()
            );

            ingredientWebs.add(ingredientWeb);
        }

        return ingredientWebs;
    }

    private List<StepWeb> getStepsFrom(Recipe recipe) {
        List<Step> steps = recipe.getSteps();
        List<StepWeb> stepsWeb = new ArrayList<>();

        for (Step step : steps) {
            StepWeb stepWeb = new StepWeb(
                    step.getOrder(),
                    step.getShortDescription(),
                    step.getDescription(),
                    step.getVideoUrl(),
                    step.getThumbnailUrl()
            );


            stepsWeb.add(stepWeb);
        }

        return stepsWeb;
    }
}
