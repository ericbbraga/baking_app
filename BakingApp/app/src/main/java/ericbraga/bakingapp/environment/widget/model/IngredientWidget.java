package ericbraga.bakingapp.environment.widget.model;

public class IngredientWidget {

    private String mName;
    private String mMeasure;
    private float mQuantity;

    public IngredientWidget(String name, String measure, float quantity) {
        mName = name;
        mMeasure = measure;
        mQuantity = quantity;
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
}
