package ericbraga.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    private long mId;
    private String mName;
    private List<Ingredient> mIngredients;
    private List<Step> mSteps;
    private boolean mStarred;

    public Recipe(String name, List<Ingredient> ingredients, List<Step> steps,
                  boolean starred) {
        this(0, name, ingredients, steps, starred);
    }

    public Recipe(long id, String name, List<Ingredient> ingredients, List<Step> steps,
                  boolean starred) {
        mId = id;
        mName = name;
        mIngredients = new ArrayList<>(ingredients);
        mSteps = new ArrayList<>(steps);
        mStarred = starred;
    }

    protected Recipe(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mIngredients = in.createTypedArrayList(Ingredient.CREATOR);
        mSteps = in.createTypedArrayList(Step.CREATOR);
        mStarred = in.readByte() != 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getName() {
        return mName;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    public List<Step> getSteps() {
        return mSteps;
    }

    public long getId() {
        return mId;
    }

    public void setStarred(boolean starred) {
        mStarred = starred;
    }

    public boolean isStarred() {
        return mStarred;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeTypedList(mIngredients);
        dest.writeTypedList(mSteps);
        dest.writeByte((byte) (mStarred ? 1 : 0));
    }
}
