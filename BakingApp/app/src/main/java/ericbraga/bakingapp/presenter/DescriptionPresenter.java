package ericbraga.bakingapp.presenter;

import java.util.List;

import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionPresenter implements DescriptionRecipeContract.Presenter {
    private final Recipe mRecipe;
    private final DescriptionRecipeContract.Router mRouter;
    private DescriptionRecipeContract.View mView;

    public DescriptionPresenter(DescriptionRecipeContract.Router router,
                                Recipe recipe) {
        mRouter = router;
        mRecipe = recipe;
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
        if (mView != null) {
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
        if (mView != null) {
            List<Ingredient> ingredients = mRecipe.getIngredients();
            mView.showIngredients(ingredients);
        }
    }

    private void loadSteps() {
        if (mView != null) {
            List<Step> steps = mRecipe.getSteps();
            mView.showSteps(steps);
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void showMoreSteps(int position) {
        mRouter.showMoreStepInfo(mRecipe.getSteps(), position);
    }
}
