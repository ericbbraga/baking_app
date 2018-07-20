package ericbraga.bakingapp.environment.android.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.android.factories.RecipePresenterFactory;
import ericbraga.bakingapp.environment.android.repositories.GlideLoader;
import ericbraga.bakingapp.environment.android.view.RecipesCardAdapter;
import ericbraga.bakingapp.environment.common.repositories.RepositoryFactory;
import ericbraga.bakingapp.environment.common.repositories.local.models.RecipeLocal;
import ericbraga.bakingapp.environment.common.repositories.web.WebRecipeReadRepository;
import ericbraga.bakingapp.interactor.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.interfaces.ReadRepository;
import ericbraga.bakingapp.interactor.interfaces.RecipeInteractor;
import ericbraga.bakingapp.interactor.implementation.LoadRecipes;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.RecipeCollection;
import ericbraga.bakingapp.presenter.interfaces.DisplayRecipesContract;
import ericbraga.bakingapp.presenter.DisplayRecipeCollectionPresenter;

public class MainActivity extends AppCompatActivity
        implements DisplayRecipesContract.View, RecipesCardAdapter.RecipesCardItemCallback {

    public static final String WEB_URL =
            "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private DisplayRecipesContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private View mNoElementsView;
    private RecipesCardAdapter mRecipesCardAdapter;
    private Looper mLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecipeAdapter();
        initViews();
        initPresenter();

        mLooper = getMainLooper();
    }

    private void initRecipeAdapter() {
        // Adapter Objects
        ImageRepository<Drawable> imageRepository = new GlideLoader(this);
        RecipePresenterFactory<Drawable> factoryPresenter =
                new RecipePresenterFactory<>(imageRepository);

        mRecipesCardAdapter = new RecipesCardAdapter(factoryPresenter);
        mRecipesCardAdapter.setRecipeCallback(this);
        // End Adapter Objects
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.baking_list);
        mRecyclerView.setAdapter(mRecipesCardAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initPresenter() {
        ReadRepository repository = RepositoryFactory.getRepository(true, this, WEB_URL);
        RecipeInteractor recipeInteractor = new LoadRecipes(repository);
        mPresenter = new DisplayRecipeCollectionPresenter(recipeInteractor);

        mPresenter.attachView(this);
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
                mRecyclerView.setVisibility(View.VISIBLE);
                mNoElementsView.setVisibility(View.INVISIBLE);
                mRecipesCardAdapter.setRecipeCollection(collection);
            }
        });
    }

    @Override
    public void showError(String message) {
    }

    @Override
    public void displayNextScreen(Recipe recipe) {
        Intent intent = new Intent(this, DescriptionRecipeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }

    @Override
    public void showEmptyList() {
        mNoElementsView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        mPresenter.recipeChosen(recipe);
    }
}
