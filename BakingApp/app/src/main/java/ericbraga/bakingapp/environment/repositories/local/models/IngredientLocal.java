package ericbraga.bakingapp.environment.repositories.local.models;

import android.arch.persistence.room.PrimaryKey;

public class IngredientLocal {
    @PrimaryKey
    private String mName;
    private String mMeasure;
    private float mQuantity;

    public IngredientLocal() {}

    public IngredientLocal(String name, float quantity, String measure) {
        mQuantity = quantity;
        mMeasure = measure;
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }
}
