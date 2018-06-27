package ericbraga.bakingapp;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.Iterator;

import ericbraga.bakingapp.boundary.RecipeCollectionWebMapper;
import ericbraga.bakingapp.environment.common.android.view.RecipesCardAdapter;
import ericbraga.bakingapp.environment.common.repositories.web.WebRecipeRepository;
import ericbraga.bakingapp.interactor.RecipeInteractor;
import ericbraga.bakingapp.interactor.implementation.LoadRecipes;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.mvpcontract.DisplayRecipesContract;
import ericbraga.bakingapp.presenter.DisplayRecipesPresenter;

public class MainActivity extends AppCompatActivity
        implements DisplayRecipesContract.View, RecipesCardAdapter.RecipesCardItemHandler {

    public static final String WEB_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private DisplayRecipesContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private RecipesCardAdapter mRecipesCardAdapter;
    private Looper mLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipesCardAdapter = new RecipesCardAdapter();
        mRecipesCardAdapter.setRecipeHandler(this);

        mRecyclerView = findViewById(R.id.baking_list);
        mRecyclerView.setAdapter(mRecipesCardAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        WebRecipeRepository repository = new WebRecipeRepository(WEB_URL, new RecipeCollectionWebMapper());
        RecipeInteractor interactor = new LoadRecipes(repository);
        mPresenter = new DisplayRecipesPresenter(interactor);

        mPresenter.attachView(this);
        mLooper = getMainLooper();
    }

    @Override
    protected void onResume() {
        super.onResume();
         mPresenter.onResume();
    }

    @Override
    public void display(final RecipeCollection collection) {
        new Handler(mLooper).post(new Runnable() {
            @Override
            public void run() {
                mRecipesCardAdapter.setRecipeCollection(collection);
            }
        });
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void displayNextScreen(Recipe recipe) {
        Toast.makeText(this, recipe.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Recipe recipe) {
        mPresenter.recipeChosen(recipe);
    }
}
