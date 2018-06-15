package ericbraga.bakingapp.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String mName;
    private List<Ingredient> mIngredients;
    private List<Step> mSteps;

    public Recipe(String name, List<Ingredient> ingredients, List<Step> steps) {
        mName = name;
        mIngredients = new ArrayList<>(ingredients);
        mSteps = new ArrayList<>(steps);
    }
}
