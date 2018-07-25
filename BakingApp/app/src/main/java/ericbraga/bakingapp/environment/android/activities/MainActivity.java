package ericbraga.bakingapp.environment.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.android.fragments.ListRecipesFragment;
import ericbraga.bakingapp.environment.common.repositories.RepositoryFactory;
import ericbraga.bakingapp.interactor.implementation.LoadRecipes;
import ericbraga.bakingapp.interactor.interfaces.ReadRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.presenter.DisplayRecipeCollectionPresenter;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;

public class MainActivity extends AppCompatActivity implements DisplayRecipesContract.Router {

    public static final String WEB_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private DisplayRecipesContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initPresenter();
        initFragment();
    }

    private void initPresenter() {
        ReadRepository repository = RepositoryFactory.getRepository(true, this, WEB_URL);
        RecipeInteractor recipeInteractor = new LoadRecipes(repository);
        mPresenter = new DisplayRecipeCollectionPresenter(this, recipeInteractor);
    }

    private void initFragment() {
        ListRecipesFragment listRecipesFragment = new ListRecipesFragment();
        listRecipesFragment.setPresenter(mPresenter);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.master_list_part, listRecipesFragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void displayNextScreen(Recipe recipe) {
        Intent intent = new Intent(this, DescriptionRecipeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }
}
