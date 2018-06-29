package ericbraga.bakingapp.environment.common.repositories.web.models;

import com.google.gson.annotations.SerializedName;

public class IngredientWeb {

    @SerializedName("quantity")
    private float mQuantity;

    @SerializedName("measure")
    private String mMeasure;

    @SerializedName("ingredient")
    private String mName;

    public IngredientWeb() {}

    public IngredientWeb(String name, float quantity, String measure) {
        mName = name;
        mQuantity = quantity;
        mMeasure = measure;
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
