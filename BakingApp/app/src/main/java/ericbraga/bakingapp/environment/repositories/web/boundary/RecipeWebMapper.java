package ericbraga.bakingapp.environment.repositories.web.boundary;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.environment.repositories.web.models.IngredientWeb;
import ericbraga.bakingapp.environment.repositories.web.models.RecipeWeb;
import ericbraga.bakingapp.environment.repositories.web.models.StepWeb;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;

public class RecipeWebMapper {
    public Recipe fromWebRecipe(RecipeWeb recipeWeb) {
        long id = recipeWeb.getId();
        String name = recipeWeb.getName();
        List<Ingredient> ingredients = getIngredientsFrom(recipeWeb);
        List<Step> steps = getStepsFrom(recipeWeb);

        return new Recipe(id, name, ingredients, steps, false);
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
}
