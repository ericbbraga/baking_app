package ericbraga.bakingapp.environment.factories;

import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeDisplayInteractor;
import ericbraga.bakingapp.interactor.implementation.LoadRecipeContents;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.presenter.interfaces.RecipeContract;
import ericbraga.bakingapp.presenter.RecipePresenter;

public class RecipePresenterFactory<T> {

    private final ImageRepository<T> mImageRepository;

    public RecipePresenterFactory(ImageRepository<T> repository) {
        mImageRepository = repository;
    }

    public RecipeContract.Presenter<T> createPresenter(Recipe recipe) {
        RecipeDisplayInteractor<T> recipeDisplayInteractor =
                new LoadRecipeContents<>(mImageRepository);

        return new RecipePresenter<>(recipeDisplayInteractor, recipe);
    }

}
