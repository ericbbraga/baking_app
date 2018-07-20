package ericbraga.bakingapp.environment.common.repositories.local.models;

import com.google.gson.annotations.SerializedName;

public class IngredientLocal {

    @SerializedName("ingredient")
    private String mName;

    @SerializedName("measure")
    private String mMeasure;

    @SerializedName("quantity")
    private float mQuantity;

    public IngredientLocal(String name, float quantity, String measure) {
        mQuantity = quantity;
        mMeasure = measure;
        mName = name;
    }

    public IngredientLocal() {
    }

    public float getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public String getName() {
        return mName;
    }
}
