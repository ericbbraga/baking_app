package ericbraga.bakingapp.environment.android.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ericbraga.bakingapp.R;
import ericbraga.bakingapp.environment.android.repositories.GlideLoader;
import ericbraga.bakingapp.environment.android.view.IngredientsAdapter;
import ericbraga.bakingapp.environment.android.view.StepsAdapter;
import ericbraga.bakingapp.environment.common.interfaces.ImageRepository;
import ericbraga.bakingapp.interactor.implementation.LoadRecipeContents;
import ericbraga.bakingapp.interactor.interfaces.RecipeDisplayInteractor;
import ericbraga.bakingapp.model.Ingredient;
import ericbraga.bakingapp.model.Recipe;
import ericbraga.bakingapp.model.Step;
import ericbraga.bakingapp.presenter.DescriptionPresenter;
import ericbraga.bakingapp.presenter.interfaces.DescriptionRecipeContract;

public class DescriptionRecipeActivity extends AppCompatActivity implements
        DescriptionRecipeContract.View<Drawable>, StepsAdapter.StepsAdapterCallback {

    private DescriptionRecipeContract.Presenter<Drawable> mPresenter;

    private ImageView mImageRecipe;
    private TextView mNameRecipe;
    private RecyclerView mIngredientsRecyclerView;
    private RecyclerView mStepsRecyclerView;
    private StepsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Recipe recipe = (Recipe) getIntent().getExtras().get("Recipe");

        setContentView(R.layout.activity_description_recipe);
        mImageRecipe = findViewById(R.id.description_recipe_image);
        mNameRecipe = findViewById(R.id.description_recipe_name);
        mIngredientsRecyclerView = findViewById(R.id.description_ingredients);
        mStepsRecyclerView = findViewById(R.id.description_steps);

        RecipeDisplayInteractor<Drawable> interactor = createInteractor();
        mPresenter = new DescriptionPresenter<>(recipe, interactor);
        mPresenter.attachView(this);

        mAdapter = new StepsAdapter();
        mAdapter.setCallback(this);

        mStepsRecyclerView.setAdapter(mAdapter);
        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private RecipeDisplayInteractor<Drawable> createInteractor() {
        ImageRepository<Drawable> imageRepository = new GlideLoader(this);
        return new LoadRecipeContents<>(imageRepository);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showImageRecipe(Drawable image) {
        mImageRecipe.setImageDrawable(image);
    }

    @Override
    public void showDescriptionRecipe(String recipeName) {
        mNameRecipe.setText(recipeName);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients);
        mIngredientsRecyclerView.setAdapter(adapter);
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showSteps(List<Step> steps) {
        mAdapter.setSteps(steps);
    }

    @Override
    public void showMoreStepInfo(Step step) {
        Intent it = new Intent(this, StepInformationActivity.class);
        it.putExtra("Step", step);
        startActivity(it);
    }

    @Override
    public void onClickItem(Step step) {
        mPresenter.onClickedItem(step);
    }
}
