package ericbraga.bakingapp.environment.common.repositories.web.models;

import com.google.gson.annotations.SerializedName;

public class IngredientWeb {

    @SerializedName("quantity")
    private float mQuantity;

    @SerializedName("measure")
    private int mMeasure;

    @SerializedName("ingrediente")
    private String mName;

    public IngredientWeb() {
    }

    public float getQuantity() {
        return mQuantity;
    }

    public int getMeasure() {
        return mMeasure;
    }

    public String getName() {
        return mName;
    }
}
