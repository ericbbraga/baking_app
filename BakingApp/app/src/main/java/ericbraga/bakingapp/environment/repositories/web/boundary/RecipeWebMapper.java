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
        List<IngredientWeb> webIngredients = recipeWeb.getIngredientWeb();
        List<Ingredient> ingredients = new ArrayList<>();


        for (IngredientWeb webIngredient : webIngredients) {
            Ingredient ingredient = new Ingredient(
                webIngredient.getName(),
                webIngredient.getQuantity(),
                webIngredient.getMeasure()
            );

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    @NonNull
    private List<Step> getStepsFrom(RecipeWeb recipeWeb) {
        List<StepWeb> webSteps = recipeWeb.getStepWebs();
        List<Step> steps = new ArrayList<>();

        for (StepWeb webStep : webSteps) {
            Step step = new Step(
                webStep.id(),
                webStep.getShortDescription(),
                webStep.getDescription(),
                webStep.getVideoUrl(),
                webStep.getThumbnailUrl()
            );

            steps.add(step);
        }

        return steps;
    }
}
