package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.interactor.interfaces.RecipeDisplayInteractor;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionPresenter<T> implements DescriptionRecipeContract.Presenter<T>,
        RecipeDisplayInteractor.Callback<T> {
    private final Recipe mRecipe;
    private final RecipeDisplayInteractor<T> mInteractor;
    private DescriptionRecipeContract.View<T> mView;

    public DescriptionPresenter(Recipe recipe, RecipeDisplayInteractor<T> interactor) {
        mRecipe = recipe;
        mInteractor = interactor;
    }

    @Override
    public void attachView(DescriptionRecipeContract.View<T> view) {
        mView = view;
        mInteractor.loadRecipeInformation(mRecipe, this);
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onResume() {
        if (mView != null) {
            setRecipeName(mRecipe.getName());
            setIngredients();
            setSteps();
        }
    }

    private void setRecipeName(String recipe) {
        if (mView != null) {
            mView.showDescriptionRecipe(recipe);
        }
    }

    private void setIngredients() {
        if (mView != null) {
            List<Ingredient> ingredients = mRecipe.getIngredients();
            mView.showIngredients(ingredients);
        }
    }

    private void setSteps() {
        if (mView != null) {
            mView.showSteps(mRecipe.getSteps());
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onClickedItem(Step step) {
        mView.showMoreStepInfo(step);
    }

    @Override
    public void onSuccess(T image) {
        mView.showImageRecipe(image);
    }

    @Override
    public void displayFallbackImage() {

    }

    @Override
    public void onError(String message) {

    }
}
