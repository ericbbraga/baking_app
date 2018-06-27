package ericbraga.bakingapp.model;

public class Ingredient {

    public class Measure {
        public static final int CUP = 0;
        public static final int TBLSP = 1;
        public static final int TSP = 2;
    }

    private String mName;
    private float mQuantity;
    private String mMeasure;

    public Ingredient(String name, float quantity, String measure) {
        mQuantity = quantity;
        mMeasure = measure;
        mName = name;
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
