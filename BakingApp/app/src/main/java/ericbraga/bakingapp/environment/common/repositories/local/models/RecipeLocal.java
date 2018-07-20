package ericbraga.bakingapp.environment.common.repositories.local.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ericbraga.bakingapp.environment.common.repositories.web.models.IngredientWeb;

public class RecipeLocal {
    @SerializedName("name")
    private String mName;

    @SerializedName("ingredients")
    private List<IngredientLocal> mIngredients;

    @SerializedName("steps")
    private List<StepLocal> mSteps;

    public RecipeLocal() {
    }

    public RecipeLocal(String name, List<IngredientLocal> ingredients, List<StepLocal> steps) {
        mName = name;
        mIngredients = new ArrayList<>(ingredients);
        mSteps = new ArrayList<>(steps);
    }

    public String getName() {
        return mName;
    }

    public Iterator<IngredientLocal> getIngredients() {
        return mIngredients.iterator();
    }

    public Iterator<StepLocal> getSteps() {
        return mSteps.iterator();
    }
}
