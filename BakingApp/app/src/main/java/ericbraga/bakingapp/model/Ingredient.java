package ericbraga.bakingapp.model;

public class Ingredient {

    public class Measure {
        public static final int CUP = 0;
        public static final int TBLSP = 1;
        public static final int TSP = 2;
    }

    private float mQuantity;
    private int mMeasure;
    private String mName;

    public Ingredient(String name, float quantity, int measure) {
        mQuantity = quantity;
        mMeasure = measure;
        mName = name;
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
