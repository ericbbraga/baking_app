package ericbraga.bakingapp.environment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.application.App;
import ericbraga.bakingapp.environment.fragments.ListRecipesFragment;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;

public class MainActivity extends AppCompatActivity implements DisplayRecipesContract.Router {

    @Inject
    DisplayRecipesContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ((App) getApplication()).inject(this);

        mPresenter.setRouter(this);
        initFragment();
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
        intent.putExtra(DescriptionRecipeActivity.RECIPE_BUNBLE_KEY, recipe);
        startActivity(intent);
    }
}
