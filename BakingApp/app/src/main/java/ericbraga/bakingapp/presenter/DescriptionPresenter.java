package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionPresenter implements DescriptionRecipeContract.Presenter {
    private static final int INVALID_STEP_SELECTED = -1;
    private DescriptionRecipeContract.Router mRouter;
    private Recipe mRecipe;
    private DescriptionRecipeContract.View mView;
    private int mStepSelected;

    public DescriptionPresenter() {
        mStepSelected = INVALID_STEP_SELECTED;
    }

    @Override
    public void setRecipe(Recipe recipe) {
        mRecipe = recipe;
    }

    @Override
    public void setStepSelect(int currentStepIndex) {
        mStepSelected = currentStepIndex;
    }

    @Override
    public void setRouter(DescriptionRecipeContract.Router router){
        mRouter = router;
    }

    @Override
    public void attachView(DescriptionRecipeContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onResume() {
        if (hasValidConfig()) {
            setRecipeName(mRecipe.getName());
            loadIngredients();
            loadSteps();
        }
    }

    private void setRecipeName(String recipe) {
        if (mView != null) {
            mView.showDescriptionRecipe(recipe);
        }
    }

    private void loadIngredients() {
        if (hasValidConfig()) {
            List<Ingredient> ingredients = mRecipe.getIngredients();
            mView.showIngredients(ingredients);
        }
    }

    private void loadSteps() {
        if (hasValidConfig()) {
            List<Step> steps = mRecipe.getSteps();
            mView.showSteps(steps, mStepSelected);
        }
    }

    @Override
    public void showMoreSteps(int position) {
        if (hasValidConfig()) {
            mRouter.showMoreStepInfo(mRecipe.getSteps(), position);
        }
    }

    private boolean hasValidConfig() {
        return mView != null && mRecipe != null && mRouter != null;
    }

    @Override
    public void onPause() {

    }
}
