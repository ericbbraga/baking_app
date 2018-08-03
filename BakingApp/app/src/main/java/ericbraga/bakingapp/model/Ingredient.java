package ericbraga.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable{

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    private String mName;
    private float mQuantity;
    private String mMeasure;

    public Ingredient(String name, float quantity, String measure) {
        mQuantity = quantity;
        mMeasure = measure;
        mName = name;
    }

    protected Ingredient(Parcel in) {
        mName = in.readString();
        mQuantity = in.readFloat();
        mMeasure = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeFloat(mQuantity);
        parcel.writeString(mMeasure);
    }
}
