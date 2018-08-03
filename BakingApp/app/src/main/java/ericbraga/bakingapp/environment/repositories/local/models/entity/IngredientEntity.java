package ericbraga.bakingapp.environment.repositories.local.models.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import ericbraga.bakingapp.environment.repositories.local.database.contract.BakingContract;

@Entity(tableName = BakingContract.IngredientEntry.TABLE_NAME)
public class IngredientEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int mId;

    @ColumnInfo(name="name")
    private String mName;

    @ColumnInfo(name="measure")
    private String mMeasure;

    @ColumnInfo(name="quantity")
    private float mQuantity;

    @ColumnInfo(name="recipe_id")
    private long mRecipeId;


    public IngredientEntity() {
    }

    @Ignore
    public IngredientEntity(String name, String measure, float quantity, long recipeId) {
        mName = name;
        mMeasure = measure;
        mQuantity = quantity;
        mRecipeId = recipeId;
    }

    @Ignore
    public IngredientEntity(int id, String name, String measure, float quantity, long recipeId) {
        mId = id;
        mName = name;
        mMeasure = measure;
        mQuantity = quantity;
        mRecipeId = recipeId;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public float getQuantity() {
        return mQuantity;
    }

    public long getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(long recipeId) {
        mRecipeId = recipeId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setMeasure(String measure) {
        mMeasure = measure;
    }

    public void setQuantity(float quantity) {
        mQuantity = quantity;
    }
}
