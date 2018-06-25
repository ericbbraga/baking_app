package ericbraga.bakingapp.environment.common.repositories.web.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeWeb {
    @SerializedName("name")
    private String mName;

    @SerializedName("ingredients")
    private List<IngredientWeb> mIngredientWebs;

    @SerializedName("steps")
    private List<StepWeb> mStepWebs;

    public RecipeWeb() {}

    public String getName() {
        return mName;
    }

    public List<IngredientWeb> getIngredientWebs() {
        return mIngredientWebs;
    }

    public List<StepWeb> getStepWebs() {
        return mStepWebs;
    }
}
