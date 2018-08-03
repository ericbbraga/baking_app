package ericbraga.bakingapp.environment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.fragments.ListRecipesFragment;
import ericbraga.bakingapp.environment.interfaces.AsyncWriteRepository;
import ericbraga.bakingapp.environment.interfaces.WriteRepository;
import ericbraga.bakingapp.environment.repositories.RepositoryFactory;
import ericbraga.bakingapp.interactor.implementation.RecipeInteractorImplementation;
import ericbraga.bakingapp.interactor.interfaces.AsyncReadRepository;
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
        AsyncReadRepository repository = RepositoryFactory.getRepository(this, WEB_URL);
        WriteRepository writeRepository = RepositoryFactory.getWriteRepository(this);

        RecipeInteractor recipeInteractor =
                new RecipeInteractorImplementation(repository, writeRepository);

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
    public void displayNextScreen(Recipe recipe) {
        Intent intent = new Intent(this, DescriptionRecipeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }
}
