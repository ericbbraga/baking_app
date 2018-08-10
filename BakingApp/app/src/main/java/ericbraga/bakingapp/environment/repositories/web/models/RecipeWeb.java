package ericbraga.bakingapp.environment.repositories.web.models;

import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.List;

public class RecipeWeb {
    @SerializedName("id")
    private long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("ingredients")
    private List<IngredientWeb> mIngredientWeb;

    @SerializedName("steps")
    private List<StepWeb> mStepWebs;

    public RecipeWeb(long id, String name, List<IngredientWeb> ingredientWeb,
                     List<StepWeb> stepWebs) {
        mId = id;
        mName = name;
        mIngredientWeb = ingredientWeb;
        mStepWebs = stepWebs;
    }

    public long getId() { return mId; }

    public String getName() {
        return mName;
    }

    public Iterator<IngredientWeb> getIngredientWeb() {
        return mIngredientWeb.iterator();
    }

    public Iterator<StepWeb> getStepWebs() {
        return mStepWebs.iterator();
    }
}