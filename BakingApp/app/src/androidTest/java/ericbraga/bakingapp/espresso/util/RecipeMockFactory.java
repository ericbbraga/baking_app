package ericbraga.bakingapp.espresso.util;

import java.util.List;

import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;

public class RecipeMockFactory {
    public static Recipe createMockRecipe() {
        List<Step> steps = StepMockFactory.createMockSteps();
        List<Ingredient> ingredients = IngredientMockFactory.createIngredients();
        return new Recipe("Cake", ingredients, steps, false);
    }
}
