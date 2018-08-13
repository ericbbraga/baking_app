package ericbraga.bakingapp.espresso.util;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.model.Ingredient;

public class IngredientMockFactory {

    private static final int TOTAL_INGREDIENTS = 5;

    public static List<Ingredient> createIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();

        for (int i = 0; i < TOTAL_INGREDIENTS; i++) {
            ingredients.add(
                new Ingredient(
                    String.format("Ingredient %d", (i + 1)),
                    i,
                    "Cup"
                )
            );
        }

        return ingredients;
    }

}
