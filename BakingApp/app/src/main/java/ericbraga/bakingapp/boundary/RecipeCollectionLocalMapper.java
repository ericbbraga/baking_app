package ericbraga.bakingapp.boundary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.environment.common.repositories.local.models.IngredientLocal;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocalCollection;
import ericbraga.bakingapp.environment.common.repositories.local.models.StepLocal;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.model.Step;

public class RecipeCollectionLocalMapper {

    public RecipeCollection fromLocalRecipe(RecipeLocalCollection recipeLocalCollection) {
        RecipeCollection collection = new RecipeCollection();
        Iterator<RecipeLocal> it = recipeLocalCollection.iterator();

        while (it.hasNext()) {
            RecipeLocal recipeLocal = it.next();

            String name = recipeLocal.getName();
            List<Ingredient> ingredients = getIngredientsFrom(recipeLocal);
            List<Step> steps = getStepsFrom(recipeLocal);

            Recipe recipe = new Recipe(name, ingredients, steps);
            collection.addRecipe(recipe);
        }

        return collection;
    }

    private List<Ingredient> getIngredientsFrom(RecipeLocal recipeLocal) {
        Iterator<IngredientLocal> iteratorIngredient = recipeLocal.getIngredients();
        List<Ingredient> ingredients = new ArrayList<>();

        while (iteratorIngredient.hasNext()) {
            IngredientLocal ingredientLocal = iteratorIngredient.next();

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
        Iterator<StepLocal> iteratorSteps = recipeLocal.getSteps();
        List<Step> steps = new ArrayList<>();

        while (iteratorSteps.hasNext()) {
            StepLocal stepLocal = iteratorSteps.next();

            Step step = new Step(
                    stepLocal.getOrder(),
                    stepLocal.getShortDescription(),
                    stepLocal.getDescription(),
                    stepLocal.getVideoUrl(),
                    stepLocal.getThumbnailUrl()
            );

            steps.add(step);
        }
        return steps;
    }

    public RecipeLocalCollection toLocalRecipe(RecipeCollection collection) {
        List<RecipeLocal> recipesLocal = new ArrayList<>();
        Iterator<Recipe> it = collection.iterator();

        while (it.hasNext()) {
            Recipe recipe = it.next();

            String name = recipe.getName();
            List<IngredientLocal> ingredients = getIngredientsFrom(recipe);
            List<StepLocal> steps = getStepsFrom(recipe);

            RecipeLocal recipeLocal = new RecipeLocal(name, ingredients, steps);
            recipesLocal.add(recipeLocal);
        }

        return new RecipeLocalCollection(recipesLocal);
    }


    private List<IngredientLocal> getIngredientsFrom(Recipe recipe) {
        List<Ingredient> ingredients = recipe.getIngredients();
        List<IngredientLocal> ingredientsLocal = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            IngredientLocal ingredientLocal = new IngredientLocal(
                    ingredient.getName(),
                    ingredient.getQuantity(),
                    ingredient.getMeasure()
            );

            ingredientsLocal.add(ingredientLocal);
        }

        return ingredientsLocal;
    }

    private List<StepLocal> getStepsFrom(Recipe recipe) {
        List<Step> steps = recipe.getSteps();
        List<StepLocal> stepsLocal = new ArrayList<>();

        for (Step step : steps) {
            StepLocal stepLocal = new StepLocal(
                    step.getOrder(),
                    step.getShortDescription(),
                    step.getDescription(),
                    step.getVideoUrl(),
                    step.getThumbnailUrl()
            );


            stepsLocal.add(stepLocal);
        }

        return stepsLocal;
    }

}
