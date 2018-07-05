package ericbraga.bakingapp.environment.android.interfaces;

public interface ImageContainer {
    void showProgress();
    void setTitleRecipe(String title);
    void loadRecipeImage(String url);
    void hideProgress();
}
