package ericbraga.bakingapp.environment.widget.model;

public class RecipeWidget {
    private long mId;

    private String mName;

    public RecipeWidget(long id, String name) {
        mId = id;
        mName = name;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
