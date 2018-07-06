package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.interactor.interfaces.RecipeDisplayInteractor;
import ericbraga.bakingapp.interactor.interfaces.StepInteractor;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionPresenter<T> implements DescriptionRecipeContract.Presenter<T> {
    private final Recipe mRecipe;
    private final RecipeDisplayInteractor<T> mRecipeInteractor;
    private final StepInteractor<T> mStepInteractor;
    private DescriptionRecipeContract.View<T> mView;
    private Step mFirstStep;

    public DescriptionPresenter(Recipe recipe,
                                RecipeDisplayInteractor<T> recipeInteractor,
                                StepInteractor<T> stepInteractor) {
        mRecipe = recipe;
        mRecipeInteractor = recipeInteractor;
        mStepInteractor = stepInteractor;
    }

    @Override
    public void attachView(DescriptionRecipeContract.View<T> view) {
        mView = view;
        mRecipeInteractor.loadRecipeInformation(mRecipe, recipeCallback());
    }

    private RecipeDisplayInteractor.Callback<T> recipeCallback() {
        return new RecipeDisplayInteractor.Callback<T>() {

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
        };
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
            loadStepPreview();
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

    private void loadStepPreview() {
        List<Step> steps = mRecipe.getSteps();
        int size = steps.size();

        if (size > 0) {
            mFirstStep = steps.get(0);
            mStepInteractor.loadStepInformation(mFirstStep, stepCallback());
        }
    }

    private StepInteractor.Callback<T> stepCallback() {
        return new StepInteractor.Callback<T>() {

            @Override
            public void onSuccess(T image) {
                mView.showStepPreview(image);
                mView.showStepName(mFirstStep.getShortDescription());
            }

            @Override
            public void displayFallbackImage() {

            }

            @Override
            public void onError(String message) {

            }
        };
    }

    @Override
    public void onPause() {

    }

    @Override
    public void showMoreSteps() {
        mView.showMoreStepInfo(mFirstStep);
    }
}
